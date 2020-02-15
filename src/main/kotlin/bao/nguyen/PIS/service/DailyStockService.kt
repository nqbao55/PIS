package bao.nguyen.PIS.service

import bao.nguyen.PIS.entity.PisBakery
import bao.nguyen.PIS.entity.PisCake
import bao.nguyen.PIS.entity.PisDailyStock
import bao.nguyen.PIS.repository.PisBakeryRepository
import bao.nguyen.PIS.repository.PisCakeRepository
import bao.nguyen.PIS.repository.PisDailyStockRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class DailyStockService {
    @Autowired
    lateinit var pisDailyStockRepository: PisDailyStockRepository

    @Autowired
    lateinit var cakeRepository: PisCakeRepository

    fun getListDailyStock():Map<PisBakery?,List<PisDailyStock>>?{
        return pisDailyStockRepository.findAll().groupBy { it.pisBakery }
    }

    fun getListCake():List<PisCake>{
        return cakeRepository.findAll()
    }
}