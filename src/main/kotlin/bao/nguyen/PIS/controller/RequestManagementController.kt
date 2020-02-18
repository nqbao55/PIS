package bao.nguyen.PIS.controller

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping

@Controller
class RequestManagementController {
    @GetMapping("/requestmanagement")
    fun detail(): String {
        return "RequestManagement"
    }

    @GetMapping("/addnewrequest")
    fun addNewRequest(): String {
        return "NewRequest"
    }

}