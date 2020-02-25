/*
 * Java entity class for "PisDelivery" 
 * Created on 2020/02/23 - Time 22:53:43 
 * Generated by Telosys Tools Generator ( version 3.0.0 )
 */
package bao.nguyen.PIS.entity;

import bao.nguyen.PIS.common.BaseEntity

import javax.persistence.*
import javax.validation.constraints.*;
import org.hibernate.annotations.CreationTimestamp
import org.hibernate.annotations.UpdateTimestamp

import java.util.Date;

 
/**
 * Domain class for entity "PisDelivery"
 *
 * @author Bao Nguyen
 *
 */
@Entity
@Table(name ="pis_delivery")
data class PisDelivery (
    //----------------------------------------------------------------------
    // ENTITY DATA FIELDS 
    //----------------------------------------------------------------------    
    // 
     var createAt : Date? = null,
    
    // PisDeliveryDetail
    @OneToMany(mappedBy="pisDelivery")
    var listOfPisDeliveryDetail:List<PisDeliveryDetail> = emptyList() 
): BaseEntity<Int>()