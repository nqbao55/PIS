package bao.nguyen.PIS.controller

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping

@Controller
class HomeController {
    @GetMapping("/")
    fun index(): String {
        return "redirect:/home"
    }

    @GetMapping("/home")
    fun home(): String {
        return "Home"
    }
}