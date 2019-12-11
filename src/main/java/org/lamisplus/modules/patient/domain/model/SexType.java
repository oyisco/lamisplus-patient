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

/**
 *
 * @author user10
 */
@Entity
@Data
@EqualsAndHashCode(of = "id")
@Table(name = "sex_type")
public class SexType implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Long id;

    @Size(max = 45)
    @Column(name = "sex")
    private String sex;
}
