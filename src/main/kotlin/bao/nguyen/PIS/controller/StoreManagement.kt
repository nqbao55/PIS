package bao.nguyen.PIS.controller

import bao.nguyen.PIS.service.DailySaleService
import bao.nguyen.PIS.service.StoreService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping

@Controller
class StoreManagement {
    @Autowired
    lateinit var storeService: StoreService

    @GetMapping("/storemanagement")
    fun storeManagement(model: Model): String {
        // get list stock
        val listStore = storeService.getListStore()
        model.addAttribute("listStore",listStore)

        // get list bakery
        val listBakery = storeService.getListBakery()
        model.addAttribute("listBakery",listBakery)
        return "StoreManagement"
    }
}