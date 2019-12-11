/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.lamisplus.modules.patient.domain.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Set;

/**
 * @author user10
 */
@Entity
@Data
@EqualsAndHashCode(of = "id")
@Table(name = "person")
public class Person implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Long id;

    @Column(name = "dob")
    private LocalDate dob;

    @Column(name = "dob_estimated")
    private Boolean dobEstimated;

    @JoinColumn(name = "sex_type_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private SexType sexTypeId;

    @OneToMany(cascade = CascadeType.MERGE, mappedBy = "personId")
    private Set<PersonContact> personContact;
}
