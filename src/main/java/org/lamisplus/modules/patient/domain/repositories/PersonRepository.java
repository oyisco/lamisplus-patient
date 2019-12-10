package org.lamisplus.modules.patient.domain.repositories;

import org.lamisplus.modules.patient.domain.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonRepository extends  JpaRepository<Person, Long>{
}
