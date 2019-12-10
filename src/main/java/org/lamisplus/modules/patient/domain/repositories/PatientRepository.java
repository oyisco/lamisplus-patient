package org.lamisplus.modules.patient.domain.repositories;

import org.lamisplus.modules.patient.domain.model.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PatientRepository extends JpaRepository<Patient, Long> {
}
