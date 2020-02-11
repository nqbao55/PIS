package bao.nguyen.PIS.controller

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
@Controller
class BakeryManagementController {
    @GetMapping("/bakerymanagenment")
    fun detail(): String {
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