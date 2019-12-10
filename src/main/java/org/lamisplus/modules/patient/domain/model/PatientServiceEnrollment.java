/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.lamisplus.modules.patient.domain.model;

import lombok.Data;

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
import javax.validation.constraints.Size;

/**
 *
 * @author user10
 */
@Entity
@Data
@Table(name = "patient_service_enrollment")
public class PatientServiceEnrollment implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Long id;
    @Column(name = "enrollment_date")
    private LocalDate enrollmentDate;
    @Size(max = 45)
    @Column(name = "identifier_value")
    private String identifierValue;
    @Column(name = "exit_date")
    private LocalDate exitDate;
    @JoinColumn(name = "identifier_type_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private IdentifierType identifierTypeId;
    @JoinColumn(name = "patient_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Patient patientId;
    @JoinColumn(name = "service_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Service serviceId;

}
