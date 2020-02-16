package bao.nguyen.PIS.service

import bao.nguyen.PIS.common.BaseService
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
class DailyStockService : BaseService() {
    @Autowired
    lateinit var pisDailyStockRepository: PisDailyStockRepository

    fun getListDailyStock():Map<PisCake?,List<PisDailyStock>>?{
        return pisDailyStockRepository.findAll().groupBy { it.pisCake }
    }

    fun loadEditForm(cakeId: Int):DailyStockForm{
        var listDailyStock = pisDailyStockRepository.findByPisCakeIdOrderById(cakeId)
        var form = DailyStockForm()
        form.cake = cakeRepository.findById(cakeId).get()
        form.listDailyStock = listDailyStock.toMutableList()
        form.listId = form.listDailyStock.groupBy { it.getId()!! }.keys.toMutableList()
        form.id = form.cake.getId()!!
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