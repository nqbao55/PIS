package bao.nguyen.PIS.controller

import bao.nguyen.PIS.common.BaseController
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping

@Controller
class LoginController : BaseController(){
    @GetMapping("/login")
    fun login(): String {
        return "Login"
    }
}