package bao.nguyen.PIS.controller

import bao.nguyen.PIS.common.BaseController
import bao.nguyen.PIS.service.HomeService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.authentication.AnonymousAuthenticationToken
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping

@Controller
class HomeController : BaseController(){
    @Autowired
    lateinit var homeService: HomeService

    @GetMapping("/")
    fun index(): String {
        if (SecurityContextHolder.getContext().authentication != null &&
                SecurityContextHolder.getContext().authentication.isAuthenticated &&
                //when Anonymous AuthenticationRequest is enabled
                SecurityContextHolder.getContext().authentication !is AnonymousAuthenticationToken) {
            // check is CusUser or SupUser
            val userDetails = SecurityContextHolder.getContext().authentication.principal as UserDetails
            val user = pisUserService.findByUsername(userDetails.username)
            if (user.role == "ADMIN"){
                return "redirect:/home"
            }else{
                return "redirect:/requestmanagement"
            }
        }
        return "redirect:/home"
    }

    @GetMapping("/home")
    fun home(model: Model): String {
        var listForm = homeService.getStatus()
        model.addAttribute("form",listForm)

        var listBakery = homeService.getListBakery()
        model.addAttribute("listBakery",listBakery)

        return "Home"
    }
}