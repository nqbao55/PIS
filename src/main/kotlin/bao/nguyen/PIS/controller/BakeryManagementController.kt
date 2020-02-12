package bao.nguyen.PIS.controller

import bao.nguyen.PIS.entity.PisBakery
import bao.nguyen.PIS.service.BakeryManagementService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
@Controller
class BakeryManagementController {
    @Autowired
    lateinit var bakeryManagementService: BakeryManagementService

    @GetMapping("/bakerymanagement")
    fun detail(model: Model): String {
        var pisBakery: List<PisBakery> = bakeryManagementService.findAll()
        model.addAttribute("pisBakery", pisBakery)
        return "BakeryManagement"
    }

    @GetMapping("/addnewbakery")
    fun addNew(): String {
        return "NewBakery"
    }

    @GetMapping("/editbakery")
    fun edit(): String {
        return "EditBakery"
    }
}