package bao.nguyen.PIS.controller

import bao.nguyen.PIS.common.BaseController
import bao.nguyen.PIS.service.HomeService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping

@Controller
class HomeController : BaseController(){
    @Autowired
    lateinit var homeService: HomeService

    @GetMapping("/")
    fun index(): String {
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