package bao.nguyen.PIS.service

import bao.nguyen.PIS.common.BaseService
import bao.nguyen.PIS.entity.PisBakery
import bao.nguyen.PIS.entity.PisCake
import bao.nguyen.PIS.entity.PisDelivery
import bao.nguyen.PIS.form.DeliveryForm
import bao.nguyen.PIS.form.HomeForm
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

    fun initPreviewForm(deliveryId: Int):Map<PisCake,List<DeliveryForm>>{
        var listForm = mutableListOf<DeliveryForm>()
        var delivery = pisDeliveryRepository.findById(deliveryId).get()
        for (detail in delivery.listOfPisDeliveryDetail){
            var form = DeliveryForm()
            form.bakery = detail.pisBakery!!
            form.cake = detail.pisCake!!
            form.create_at = detail.createAt!!
            form.pieces = detail.piece!!
            form.tray = detail.tray!!
            listForm.add(form)
        }
        return listForm.groupBy { it.cake }
    }

    fun getById(deliveryId: Int): PisDelivery?{
        return pisDeliveryRepository.findById(deliveryId).get()
    }
}