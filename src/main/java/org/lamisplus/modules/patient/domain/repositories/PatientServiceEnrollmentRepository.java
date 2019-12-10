package org.lamisplus.modules.patient.domain.repositories;

import org.lamisplus.modules.patient.domain.model.PatientServiceEnrollment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PatientServiceEnrollmentRepository extends JpaRepository<PatientServiceEnrollment, Long> {
}
