package bao.nguyen.PIS.service

import bao.nguyen.PIS.common.BaseService
import bao.nguyen.PIS.entity.PisCake
import bao.nguyen.PIS.form.HomeForm
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import kotlin.math.round

@Service
class HomeService: BaseService() {

    @Autowired
    lateinit var storeService: StoreService

    fun getStatus():Map<PisCake,List<HomeForm>>{
        var listForm = mutableListOf<HomeForm>()
        var listStock = storeService.getListStore()
        for (listStock in listStock!!.values){
            for (stock in listStock){
                var form = HomeForm()
                form.cake = stock.pisCake!!
                form.bakery = stock.pisBakery!!

                var dailySale = stock.pisCake!!.listOfPisDailySale.find { it.pisBakery!!.getId() == stock.pisBakery!!.getId() }
                form.dailySale = dailySale!!.piece
                var setting = stock.pisCake!!.listOfPisSetting.find { it.pisBakery!!.getId() == stock.pisBakery!!.getId() }
                form.minStock = setting!!.minStock!!
                form.maxStock = setting!!.maxStock!!
                form.currentStock = stock.piece
                form.current = (stock.piece/form.dailySale).toFloat()
                form.miss = ((form.maxStock * form.dailySale) - form.currentStock)/form.dailySale

                listForm.add(form)
            }
        }
        return listForm.groupBy { it.cake }
    }
}