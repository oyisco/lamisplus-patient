package org.lamisplus.modules.patient.domain.repositories;

import org.lamisplus.modules.patient.domain.model.ServiceForm;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ServiceFormRepository extends JpaRepository<ServiceForm, Long> {
}
