/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.lamisplus.modules.patient.domain.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;

/**
 *
 * @author user10
 */
@Entity
@Data
@EqualsAndHashCode(of = "id")
@Table(name = "organizational_unit_attribute")
public class OrganizationalUnitAttribute implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Long id;
    @Lob
    @Size(max = 65535)
    @Column(name = "physical_location")
    private String physicalLocation;
    @Lob
    @Size(max = 65535)
    @Column(name = "postal_address")
    private String postalAddress;
    @Column(name = "start_date")
    @Temporal(TemporalType.DATE)
    private Date startDate;
    @Size(max = 45)
    @Column(name = "ownership")
    private String ownership;
    @Size(max = 45)
    @Column(name = "ownership_type")
    private String ownershipType;
    @Size(max = 45)
    @Column(name = "facility_level")
    private String facilityLevel;
    @Size(max = 45)
    @Column(name = "facility_level_option")
    private String facilityLevelOption;
    @Size(max = 45)
    @Column(name = "days_operation")
    private String daysOperation;
    @Size(max = 45)
    @Column(name = "hours_operation")
    private String hoursOperation;

    
}
