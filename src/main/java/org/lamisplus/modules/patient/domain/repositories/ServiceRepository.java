package org.lamisplus.modules.patient.domain.repositories;

import org.lamisplus.modules.patient.domain.model.Service;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ServiceRepository extends JpaRepository<Service, Long> {
}
