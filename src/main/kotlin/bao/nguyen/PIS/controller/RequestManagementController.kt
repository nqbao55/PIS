package bao.nguyen.PIS.controller

import bao.nguyen.PIS.common.BaseController
import bao.nguyen.PIS.entity.PisRequest
import bao.nguyen.PIS.entity.PisUser
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

@Controller
class RequestManagementController : BaseController(){
    @Autowired
    lateinit var requestManagementService: RequestManagementService

    @Autowired
    lateinit var cakeManagementService: CakeManagementService

    @Autowired
    lateinit var pisUserService: PisUserService

    @GetMapping("/requestmanagement")
    fun detail(model: Model): String {
        val listRequest = requestManagementService.getListRequest()
        model.addAttribute("listRequest",listRequest)
        return "RequestManagement"
    }

    @GetMapping("/addnewrequest")
    fun addNew(model: Model): String {
        if (SecurityContextHolder.getContext().authentication != null &&
                SecurityContextHolder.getContext().authentication.isAuthenticated &&
                //when Anonymous AuthenticationRequest is enabled
                SecurityContextHolder.getContext().authentication !is AnonymousAuthenticationToken) {
            // check is CusUser or SupUser
            val userDetails = SecurityContextHolder.getContext().authentication.principal as UserDetails
            var userData: PisUser = pisUserService.findByUsername(userDetails.username)
            var bakeryid: String = userData.username
            model.addAttribute("bakeryid", bakeryid)
        }
        val listCake= cakeManagementService.findAll()
        model.addAttribute("listCake", listCake)
        model.addAttribute("dataForm", PisRequestForm())
        return "NewRequest"
    }

    @PostMapping("addnewrequest")
    fun doAddPis(@Validated @ModelAttribute pisRequest: PisRequest):String{
        requestManagementService.save(pisRequest)
        return "redirect:/requestmanagement"
    }

}