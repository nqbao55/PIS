/*
 * Java entity class for "PisBakery" 
 * Created on 2020/02/11 - Time 16:31:10 
 * Generated by Telosys Tools Generator ( version 3.0.0 )
 */
package bao.nguyen.PIS.entity;

import bao.nguyen.PIS.common.BaseEntity
import javax.persistence.*
import javax.validation.constraints.*;
import org.springframework.data.annotation.Id;
import org.hibernate.annotations.CreationTimestamp
import org.hibernate.annotations.UpdateTimestamp


  
/**
 * Domain class for entity "PisBakery"
 *
 * @author Bao Nguyen
 *
 */
@Entity
@Table(name ="pis_bakery")
data class PisBakery (
    //----------------------------------------------------------------------
    // ENTITY DATA FIELDS 
    //----------------------------------------------------------------------    
    // 
     @NotNull
    @Size( min = 1, max = 255 )
    var name : String = String(),

     @NotNull
     @Size( min = 1, max = 255 )
     var username : String = String(),

	// 
    @Size( max = 255 )
    var address : String? = null,

	// 
    @Size( max = 255 )
    var phone : String? = null,

	// 
    @Size( max = 255 )
    var email : String? = null,
    
    // PisUser
    @OneToMany(mappedBy="pisBakery")
    var listOfPisUser:List<PisUser> = emptyList() ,
    
    // PisRequest
    @OneToMany(mappedBy="pisBakery")
    var listOfPisRequest:List<PisRequest> = emptyList() ,
    
    // PisSetting
    @OneToMany(mappedBy="pisBakery")
    var listOfPisSetting:List<PisSetting> = emptyList() ,
    
    // PisDeliveryDetail
    @OneToMany(mappedBy="pisBakery")
    var listOfPisDeliveryDetail:List<PisDeliveryDetail> = emptyList() ,
    
    // PisDailySale
    @OneToMany(mappedBy="pisBakery")
    var listOfPisDailySale:List<PisDailySale> = emptyList() ,
    
    // PisStore
    @OneToMany(mappedBy="pisBakery")
    var listOfPisStore:List<PisStore> = emptyList() 
): BaseEntity<Int>()
