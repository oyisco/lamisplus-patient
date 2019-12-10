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
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author user10
 */
@Entity
@Data
@EqualsAndHashCode(of = "id")
@Table(name = "visit_type")
public class VisitType implements Serializable {
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "id")
    private Long id;
    @Size(max = 45)
    @Column(name = "visit_type_name")
    private String visitTypeName;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "visitTypeId")
    private Set<PatientVisit> patientVisit;

}
