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
import java.util.Set;

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
    @Column(name = "coordinates")
    private String coordinates;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "organizationalUnitId")
    private Set<PersonContact> personContact;
    @JoinColumn(name = "organizational_unit_level_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private OrganizationalUnitLevel organizationalUnitLevelId;

}
