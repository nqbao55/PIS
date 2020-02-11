/*
 * Created on 2020-02-11 ( Date ISO 2020-02-11 - Time 16:31:10 )
 * Generated by Telosys ( http://www.telosys.org/ ) version 3.0.0
 */
package bao.nguyen.PIS.repository;

import bao.nguyen.PIS.entity.PisDailyStock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

/**
 * Spring data Jpa repository for "PisDailyStock" <br>
 * @author Telosys (http://www.telosys.org/) version 3.0.0
 */
@Repository
interface PisDailyStockRepository : JpaRepository<PisDailyStock, Int> {
}