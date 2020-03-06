package bao.nguyen.PIS.controller

import bao.nguyen.PIS.common.BaseController
import bao.nguyen.PIS.service.DeliveryService
import bao.nguyen.PIS.service.HomeService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable

@Controller
class DeliveryController : BaseController(){
    @Autowired
    lateinit var deliverryService: DeliveryService


    @GetMapping("/deliverypreview/{deliveryId}")
    fun deliveryPreview(@PathVariable(name = "deliveryId") deliveryId: Int, model: Model): String {
        var listForm = deliverryService.initPreviewForm(deliveryId)
        model.addAttribute("listForm",listForm)

        var delivery = deliverryService.getById(deliveryId)
        var listBakery = delivery!!.listOfPisDeliveryDetail.groupBy { it.pisBakery }.keys.toList()
        model.addAttribute("listBakery",listBakery)

        return "PrintPreview"
    }
}