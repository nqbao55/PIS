package bao.nguyen.PIS.service

import bao.nguyen.PIS.common.BaseService
import bao.nguyen.PIS.entity.PisCake
import bao.nguyen.PIS.entity.PisDailySale
import bao.nguyen.PIS.entity.PisSetting
import bao.nguyen.PIS.form.DailySaleForm
import bao.nguyen.PIS.repository.PisDailySaleRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class DailySaleService : BaseService() {
    @Autowired
    lateinit var pisDailySaleRepository: PisDailySaleRepository

    fun getListDailySale():Map<PisCake?,List<PisDailySale>>?{
        return pisDailySaleRepository.findAll().groupBy { it.pisCake }
    }

    fun loadEditForm(cakeId: Int):DailySaleForm{
        var listDailySale = pisDailySaleRepository.findByPisCakeIdOrderById(cakeId)
        var form = DailySaleForm()
        form.cake = cakeRepository.findById(cakeId).get()
        form.listDailySale = listDailySale.toMutableList()
        form.listId = form.listDailySale.groupBy { it.getId()!! }.keys.toMutableList()
        form.id = form.cake.getId()!!
        return form
    }

    fun updateDailySale(form: DailySaleForm){
        // get list DailySale
        form.listDailySale.forEachIndexed { index, setting ->
            var dailySale = pisDailySaleRepository.findById(form.listId[index]).get()
            dailySale.piece = form.listDailySale[index].piece
            pisDailySaleRepository.save(dailySale)
        }
    }

    fun initDailySaleForBakery(bakeryId:Int){
        var listCake = getListCake()
        for (cake in listCake){
            var dailySale = PisDailySale()
            dailySale.pisCake = cakeRepository.findById(cake.getId()!!).get()
            dailySale.pisBakery = bakeryRepository.findById(bakeryId).get()
            dailySale.piece = 1


            pisDailySaleRepository.save(dailySale)
        }
    }

    fun initDailySaleForCake(cakeId:Int){
        var listBakery = getListBakery()
        for (bakery in listBakery){
            var dailySale = PisDailySale()
            dailySale.pisCake = cakeRepository.findById(cakeId).get()
            dailySale.pisBakery = bakeryRepository.findById(bakery.getId()!!).get()
            dailySale.piece = 1
            pisDailySaleRepository.save(dailySale)
        }
    }
}