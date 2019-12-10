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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;

/**
 *
 * @author user10
 */
@Entity
@Data
@EqualsAndHashCode(of = "id")
@Table(name = "organizational_unit")
public class OrganizationalUnit implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Long id;
    @Size(max = 100)
    @Column(name = "organizational_unit_name")
    private String organizationalUnitName;
    @Size(max = 100)
    @Column(name = "short_name")
    private String shortName;
    @Size(max = 50)
    @Column(name = "code")
    private String code;
    @Size(max = 100)
    @Column(name = "cordinates")
    private String cordinates;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "organizationalUnitId")
    private Set<PersonContact> personContact;
    @JoinColumn(name = "organizational_unit_level_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private OrganizationalUnitLevel organizationalUnitLevelId;

}
