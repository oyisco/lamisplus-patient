/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.lamisplus.modules.patient.domain.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.time.LocalDate;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 *
 * @author user10
 */
@Entity
@Data
@Table(name = "patient_encounter")
@EqualsAndHashCode(of = "id")
public class PatientEncounter implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Long id;

    @Column(name = "encounter_date")
    private LocalDate encounterDate;

    @JoinColumn(name = "patient_visit_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private PatientVisit patientVisit;

    @JoinColumn(name = "service_form_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private ServiceForm serviceForm;

}
