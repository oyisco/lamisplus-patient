package org.lamisplus.modules.patient.domain.repositories.search;

import org.lamisplus.modules.patient.domain.model.Patient;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface PatientSearchRepository extends ElasticsearchRepository<Patient, Long> {
}
