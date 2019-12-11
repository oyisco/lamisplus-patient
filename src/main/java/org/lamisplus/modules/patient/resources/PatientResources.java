package org.lamisplus.modules.patient.resources;

import com.github.vanroy.springdata.jest.JestElasticsearchTemplate;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.functionscore.FunctionScoreQueryBuilder;
import org.elasticsearch.index.query.functionscore.ScoreFunctionBuilders;
import org.fhi360.lamis.modules.base.service.ElasticsearchIndexService;
import org.fhi360.lamis.modules.base.web.errors.BadRequestAlertException;
import org.fhi360.lamis.modules.base.web.util.HeaderUtil;
import org.fhi360.lamis.modules.base.web.util.PaginationUtil;
import org.lamisplus.modules.patient.domain.model.Patient;
import org.lamisplus.modules.patient.domain.repositories.PatientRepository;
import org.lamisplus.modules.patient.domain.repositories.PersonRepository;
import org.lamisplus.modules.patient.domain.repositories.search.PatientSearchRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.core.query.SearchQuery;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

import static org.elasticsearch.index.query.QueryBuilders.matchQuery;

@RestController
@RequestMapping("/api/patient")
@Slf4j
public class PatientResources {
    private static final String ENTITY_NAME = "patient";
    private final PatientRepository patientRepository;
    private final PersonRepository personRepository;
    private final PatientSearchRepository patientSearchRepository;
    private final JestElasticsearchTemplate searchTemplate;
    private final ElasticsearchIndexService indexService;

    public PatientResources(PatientRepository patientRepository, PersonRepository personRepository, PatientSearchRepository patientSearchRepository, JestElasticsearchTemplate searchTemplate, ElasticsearchIndexService indexService) {
        this.patientRepository = patientRepository;
        this.personRepository = personRepository;
        this.patientSearchRepository = patientSearchRepository;
        this.searchTemplate = searchTemplate;
        this.indexService = indexService;
    }

    @PostMapping
    public ResponseEntity<Patient> createPatient(@RequestBody Patient patient) throws URISyntaxException {
        Optional<Patient> patient1 = patientRepository.findById(patient.getId());
        patient1.map(o -> {
            throw new BadRequestAlertException("Patient Already Exist", ENTITY_NAME, "id Already Exist");
        });
        Patient result = patientRepository.save(patient);
        patientSearchRepository.save(result);
        return ResponseEntity.created(new URI("/api/patient/" + result.getId()))
                .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
                .body(result);
    }

    @PutMapping
    public ResponseEntity<Patient> updatePatient(@RequestBody Patient patient) throws URISyntaxException {
        Optional<Patient> patient1 = patientRepository.findById(patient.getId());
        patient1.orElseGet(() -> {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "id is null");
        });
        Patient result = patientRepository.save(patient);
        patientSearchRepository.save(result);
        return ResponseEntity.created(new URI("/api/patient/" + result.getId()))
                .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
                .body(result);
    }

    @GetMapping
    public ResponseEntity<List<Patient>> getAllPatients(Pageable pageable) {
        return ResponseEntity.ok(patientRepository.findAll(pageable).getContent());
    }


    @PostMapping("/search")
    public ResponseEntity<List<Patient>> getAllPatientsFromElasticSearch(@RequestParam String searchContent,
                                                                         Pageable pageable) {
        FunctionScoreQueryBuilder.FilterFunctionBuilder[] functionScoreQueryBuilder = {
                new FunctionScoreQueryBuilder.FilterFunctionBuilder(
                        QueryBuilders.boolQuery().should(matchQuery("surname", searchContent)),
                        ScoreFunctionBuilders.weightFactorFunction(1000)),
                new FunctionScoreQueryBuilder.FilterFunctionBuilder(
                        QueryBuilders.boolQuery().should(matchQuery("otherNames", searchContent)),
                        ScoreFunctionBuilders.weightFactorFunction(100)),
                new FunctionScoreQueryBuilder.FilterFunctionBuilder(
                        QueryBuilders.boolQuery().should(matchQuery("hospitalNum", searchContent)),
                        ScoreFunctionBuilders.weightFactorFunction(1000)),
                new FunctionScoreQueryBuilder.FilterFunctionBuilder(
                        QueryBuilders.boolQuery().should(matchQuery("phone", searchContent)),
                        ScoreFunctionBuilders.weightFactorFunction(1000)),
                new FunctionScoreQueryBuilder.FilterFunctionBuilder(
                        QueryBuilders.boolQuery().should(matchQuery("address", searchContent)),
                        ScoreFunctionBuilders.weightFactorFunction(1000))
        };

        QueryBuilder queryBuilder = QueryBuilders.functionScoreQuery(functionScoreQueryBuilder);
        SearchQuery searchQuery = new NativeSearchQueryBuilder()
                .withPageable(pageable)
                .withIndices("patient")
                .withTypes("patients")
                .withQuery(queryBuilder).build();
        LOG.info("\n searchCity(): searchContent [" + searchContent + "] \n DSL  = \n " + searchQuery.getQuery().toString());
        Page<Patient> searchPageResults = patientSearchRepository.search(searchQuery);
        HttpHeaders headers = PaginationUtil.generateSearchPaginationHttpHeaders(searchContent, searchPageResults, "/api/patient/search");
        return new ResponseEntity<>(searchPageResults.getContent(), headers, HttpStatus.OK);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePatient(@PathVariable Long id) {
        patientRepository.deleteById(id);
        patientSearchRepository.deleteById(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }

}
