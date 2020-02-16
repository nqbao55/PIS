package bao.nguyen.PIS.service

import bao.nguyen.PIS.entity.PisBakery
import bao.nguyen.PIS.entity.PisCake
import bao.nguyen.PIS.entity.PisDailyStock
import bao.nguyen.PIS.form.DailyStockForm
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

    @Autowired
    lateinit var bakeryRepository: PisBakeryRepository

    fun getListDailyStock():Map<PisBakery?,List<PisDailyStock>>?{
        return pisDailyStockRepository.findAll().groupBy { it.pisBakery }
    }

    fun getListCake():List<PisCake>{
        return cakeRepository.findAll()
    }

    fun loadEditForm(bakeryId: Int):DailyStockForm{
        var bakery = bakeryRepository.findById(bakeryId).get()
        var listDailyStock = pisDailyStockRepository.findByPisBakeryIdOrderById(bakeryId)
        var form = DailyStockForm()
        form.bakery = bakeryRepository.findById(bakeryId).get()
        form.listDailyStock = listDailyStock.toMutableList()
        form.listId = form.listDailyStock.groupBy { it.getId()!! }.keys.toMutableList()
        form.id = form.bakery.getId()!!
        return form
    }

    fun updateDailyStock(form: DailyStockForm){
        // get list DailyStock
        form.listDailyStock.forEachIndexed { index, setting ->
            var dailyStock = pisDailyStockRepository.findById(form.listId[index]).get()
            dailyStock.piece = form.listDailyStock[index].piece
            pisDailyStockRepository.save(dailyStock)
        }
    }
}