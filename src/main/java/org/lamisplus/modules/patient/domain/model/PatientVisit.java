/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.lamisplus.modules.patient.domain.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Set;

/**
 * @author user10
 */
@Entity
@Data
@EqualsAndHashCode(of = "id")
@Table(name = "patient_visit")
public class PatientVisit implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Long id;

    @Column(name = "visit_date_start")
    private LocalDate visitDateStart;

    @Column(name = "visit_date_end")
    private LocalDate visitDateEnd;

    @Size(max = 45)
    @Column(name = "visit_time_start")
    private String visitTimeStart;

    @Size(max = 45)
    @Column(name = "visit_time_end")
    private String visitTimeEnd;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "patientVisitId")
    private Set<PatientEncounter> patientEncounter;

    @JoinColumn(name = "patient_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Patient patient;

    @JoinColumn(name = "visit_type_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private VisitType visitType;
}
