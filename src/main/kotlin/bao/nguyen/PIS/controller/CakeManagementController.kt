package bao.nguyen.PIS.controller

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
@Controller
class CakeManagementController {
    @GetMapping("/cakemanagenment")
    fun detail(): String {
        return "CakeManagement"
    }

    @GetMapping("/addnewcake")
    fun addNew(): String {
        return "NewCake"
    }

    @GetMapping("/editcake")
    fun edit(): String {
        return "EditCake"
    }
}