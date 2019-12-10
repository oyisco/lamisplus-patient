package org.lamisplus.modules.patient.domain.repositories;

import org.lamisplus.modules.patient.domain.model.OrganizationalUnit;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrganizationalUnitRepository extends JpaRepository<OrganizationalUnit, Long> {
}
