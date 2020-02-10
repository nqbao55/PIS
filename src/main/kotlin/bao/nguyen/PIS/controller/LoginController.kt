package bao.nguyen.PIS.controller

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping

@Controller
class LoginController {
    @GetMapping("/signin")
    fun login(): String {
        return "signin"
    }

    @GetMapping("/")
    fun index(): String {
        return "redirect:/signin"
    }
}