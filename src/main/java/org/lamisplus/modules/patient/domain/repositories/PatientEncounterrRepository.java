package org.lamisplus.modules.patient.domain.repositories;

import org.lamisplus.modules.patient.domain.model.PatientEncounter;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PatientEncounterrRepository extends JpaRepository<PatientEncounter, Long> {
}
