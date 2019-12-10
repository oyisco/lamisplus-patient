package org.lamisplus.modules.patient.domain.repositories;

import org.lamisplus.modules.patient.domain.model.VisitType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VisitTypeRepository extends JpaRepository<VisitType, Long> {
}
