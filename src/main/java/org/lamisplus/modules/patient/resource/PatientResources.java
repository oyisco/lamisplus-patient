package org.lamisplus.modules.patient.resource;

import com.github.vanroy.springdata.jest.JestElasticsearchTemplate;
import org.fhi360.lamis.modules.base.service.ElasticsearchIndexService;
import org.fhi360.lamis.modules.base.web.errors.BadRequestAlertException;
import org.fhi360.lamis.modules.base.web.util.HeaderUtil;
import org.lamisplus.modules.patient.domain.model.Patient;
import org.lamisplus.modules.patient.domain.repositories.PatientRepository;
import org.lamisplus.modules.patient.domain.repositories.PersonRepository;
import org.lamisplus.modules.patient.domain.repositories.search.PatientSearchRepository;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/patients")
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
            throw new BadRequestAlertException("A new patient cannot already have an ID", ENTITY_NAME, "id Already Exist");
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













    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePatient(@PathVariable Long id) {
        patientRepository.deleteById(id);
        patientSearchRepository.deleteById(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }



}
