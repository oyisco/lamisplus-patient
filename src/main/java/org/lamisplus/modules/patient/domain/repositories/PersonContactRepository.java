package org.lamisplus.modules.patient.domain.repositories;

import org.lamisplus.modules.patient.domain.model.PersonContact;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonContactRepository extends JpaRepository<PersonContact,Long> {
}
