/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.lamisplus.modules.patient.domain.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.elasticsearch.annotations.Document;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Collection;
import java.util.Set;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

/**
 * @author user10
 */
@Entity
@Data
@EqualsAndHashCode(of = "id")
@Table(name = "patient")
@Document(indexName = "patient", type = "patients")
public class Patient implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Long id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "date_registration")
    private LocalDate dateRegistration;

    @Column(name = "person_id")
    @OneToMany(cascade = CascadeType.MERGE, mappedBy = "personId")
    private Person person;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "patientId")
    private Set<PatientServiceEnrollment> patientServiceEnrollment;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "patientId")
    private Set<PatientVisit> patientVisit;

}
