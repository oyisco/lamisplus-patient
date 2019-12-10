package org.lamisplus.modules.patient.domain.repositories;

import org.lamisplus.modules.patient.domain.model.PatientVisit;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PatientVisitRepository  extends JpaRepository<PatientVisit, Long> {
}
