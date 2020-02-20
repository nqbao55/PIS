package bao.nguyen.PIS.controller

import bao.nguyen.PIS.form.DailySaleForm
import bao.nguyen.PIS.form.StoreForm
import bao.nguyen.PIS.service.DailySaleService
import bao.nguyen.PIS.service.StoreService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import javax.validation.Valid

@Controller
class StoreManagementControlleer {
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

    @GetMapping("/editstore/{id}")
    fun editStoreOfBakery(@PathVariable(name = "id") id: Int, model: Model): String {
        // init form
        var form = storeService.initStoreForm(id)
        model.addAttribute("storeForm",form)
        return "EditStore"
    }

    @PostMapping("/saveStore")
    fun updateStore(@Valid storeForm: StoreForm, model: Model):String{
        storeService.updateStore(storeForm)
        return "redirect:/storemanagement?success"
    }
}