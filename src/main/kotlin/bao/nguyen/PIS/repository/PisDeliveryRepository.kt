/*
 * Created on 2020-02-23 ( Date ISO 2020-02-23 - Time 22:53:43 )
 * Generated by Telosys ( http://www.telosys.org/ ) version 3.0.0
 */
package bao.nguyen.PIS.repository;

import bao.nguyen.PIS.entity.PisDelivery;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

/**
 * Spring data Jpa repository for "PisDelivery" <br>
 * @author Telosys (http://www.telosys.org/) version 3.0.0
 */
@Repository
interface PisDeliveryRepository : JpaRepository<PisDelivery, Int> {
}