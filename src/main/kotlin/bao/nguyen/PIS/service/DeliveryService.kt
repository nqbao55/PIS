package bao.nguyen.PIS.service

import bao.nguyen.PIS.common.BaseService
import bao.nguyen.PIS.entity.PisBakery
import bao.nguyen.PIS.entity.PisCake
import bao.nguyen.PIS.form.DeliveryForm
import bao.nguyen.PIS.repository.PisDailySaleRepository
import bao.nguyen.PIS.repository.PisDeliveryDetailRepository
import bao.nguyen.PIS.repository.PisDeliveryRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.*

@Service
class DeliveryService : BaseService() {
    @Autowired
    lateinit var pisDeliveryRepository: PisDeliveryRepository

    @Autowired
    lateinit var pisDeliveryDetailRepository: PisDeliveryDetailRepository

    @Autowired
    lateinit var storeService: StoreService

    fun initPreviewForm(listId: List<String>):List<DeliveryForm>{
        var listForm = mutableListOf<DeliveryForm>()
        for (strId in listId){
            // get Bakery
            var bakery = bakeryRepository.findById(strId.toInt()).get()
            var listCake = getListCake()
            for (cake in listCake){
                var form = initFormOfBakeryAndCake(bakery, cake)
                listForm.add(form)
            }
        }
        return listForm.toList()
    }

    fun initFormOfBakeryAndCake(bakery:PisBakery, cake:PisCake):DeliveryForm{
        var form = DeliveryForm()
        form.bakery = bakery
        form.cake = cake
        form.create_at = Date()
        var currentStock = findStock(cake,bakery)
        var setting = findSetting(cake, bakery)
        var dailySale = findDailySale(cake,bakery)
        form.pieces = (setting.maxStock!!.toInt() * dailySale) - currentStock
        form.tray = 1.toFloat()
        return form
    }

}