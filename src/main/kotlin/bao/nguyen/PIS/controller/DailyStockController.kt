package bao.nguyen.PIS.controller

import bao.nguyen.PIS.service.BakeryManagementService
import bao.nguyen.PIS.service.DailyStockService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
@Controller
class DailyStockController {
    @Autowired
    lateinit var dailyStockService: DailyStockService

    @GetMapping("/dailystock")
    fun dailystock(model: Model): String {
        // get list dailystock
        val dailyStock = dailyStockService.getListDailyStock()
        model.addAttribute("dailyStock",dailyStock)

        // get list cake
        val listCake = dailyStockService.getListCake()
        model.addAttribute("listCake",listCake)
        return "DailyStock"
    }
}