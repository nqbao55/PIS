package bao.nguyen.PIS.controller

import bao.nguyen.PIS.common.BaseController
import bao.nguyen.PIS.entity.PisDelivery
import bao.nguyen.PIS.service.DeliveryManagementService
import bao.nguyen.PIS.service.DeliveryService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable

@Controller
class DeliveryManagementController : BaseController(){
    @Autowired
    lateinit var pisDeliveryManagementService: DeliveryManagementService

    @Autowired
    lateinit var deliveryService: DeliveryService

    @GetMapping("/deliverymanagement")
    fun delivery(model: Model): String {
        var pisDelivery: List<PisDelivery> = pisDeliveryManagementService.findAll()
        model.addAttribute("pisDelivery", pisDelivery)
        return "DeliveryManagement"
    }

    @GetMapping("/deliverydetail/{deliveryId}")
    fun deliveryDetail(@PathVariable(name = "deliveryId") deliveryId: Int, model: Model): String {
        var listForm = deliveryService.initPreviewForm(deliveryId)
        model.addAttribute("listForm",listForm)

        var delivery = deliveryService.getById(deliveryId)
        var listBakery = delivery!!.listOfPisDeliveryDetail.groupBy { it.pisBakery }.keys.toList()
        model.addAttribute("listBakery",listBakery)

        return "DeliveryDetail"
    }
}