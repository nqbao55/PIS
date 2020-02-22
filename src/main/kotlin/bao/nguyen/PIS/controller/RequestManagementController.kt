package bao.nguyen.PIS.controller

import bao.nguyen.PIS.common.BaseController
import bao.nguyen.PIS.entity.PisRequest
import bao.nguyen.PIS.entity.PisUser
import bao.nguyen.PIS.form.MinMaxStockForm
import bao.nguyen.PIS.form.PisRequestForm
import bao.nguyen.PIS.service.CakeManagementService
import bao.nguyen.PIS.service.PisUserService
import bao.nguyen.PIS.service.RequestManagementService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.authentication.AnonymousAuthenticationToken
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.ModelAttribute
import org.springframework.web.bind.annotation.PostMapping
import javax.validation.Valid

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
        return "RequestManagement"
    }

    @GetMapping("/addnewrequest")
    fun addNew(model: Model): String {
        val listCake= cakeManagementService.findAll()
        model.addAttribute("listCake", listCake)
        model.addAttribute("dataForm", PisRequestForm())
        return "NewRequest"
    }

    @PostMapping("addnewrequest")
    fun doAddPis(@Validated @ModelAttribute pisRequest: PisRequest):String{
        requestManagementService.save(pisRequest)
        return "redirect:/requestmanagement?success"
    }

}