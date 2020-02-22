package bao.nguyen.PIS.controller

import bao.nguyen.PIS.common.BaseController
import bao.nguyen.PIS.service.CakeManagementService
import bao.nguyen.PIS.service.RequestManagementService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping

@Controller
class RequestManagementController : BaseController(){
    @Autowired
    lateinit var requestManagementService: RequestManagementService

    @Autowired
    lateinit var cakeManagementService: CakeManagementService

    @GetMapping("/requestmanagement")
    fun detail(model: Model): String {
        val listRequest = requestManagementService.getListRequest()
        model.addAttribute("listRequest",listRequest)

        val listBakery =  requestManagementService.getListBakery()
        model.addAttribute("listBakery",listBakery)

        return "RequestManagement"
    }

    @GetMapping("/addnewrequest")
    fun addNewRequest(model: Model): String {
        val listCake= cakeManagementService.findAll()
        model.addAttribute("listCake", listCake)
        return "NewRequest"
    }

}