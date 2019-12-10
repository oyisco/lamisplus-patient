/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.lamisplus.modules.patient.domain.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
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
import javax.validation.constraints.Size;

/**
 *
 * @author user10
 */
@Entity
@Data
@EqualsAndHashCode(of = "id")
public class ServiceForm implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Long id;
    @Size(max = 45)
    @Column(name = "form_data")
    private String formData;
    @Size(max = 45)
    @Column(name = "service_form_name")
    private String serviceFormName;
    @Size(max = 45)
    @Column(name = "service_form_code")
    private String serviceFormCode;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "serviceFormId")
    private Set<PatientEncounter> patientEncounter;

}
