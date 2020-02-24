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


    @GetMapping("/deliverypreview/{strId}")
    fun deliveryPreview(@PathVariable(name = "strId") strId: String, model: Model): String {
        var listId = strId.split(",")
        var listForm = deliverryService.initPreviewForm(listId)
        model.addAttribute("listForm",listForm)

        return "PrintPreview"
    }
}