package bao.nguyen.PIS.controller

import bao.nguyen.PIS.common.BaseController
import bao.nguyen.PIS.form.MinMaxStockForm
import bao.nguyen.PIS.service.MinMaxStockService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import javax.validation.Valid

@Controller
class MinMaxStockController: BaseController(){
    @Autowired
    lateinit var minMaxStockService: MinMaxStockService

    @GetMapping("/minmaxstock")
    fun minMaxStock(model: Model): String {
        // get list setting
        val minMaxStock = minMaxStockService.getListSetting()
        model.addAttribute("minMaxStock",minMaxStock)

        // get list bakery
        val listBakery = minMaxStockService.getListBakery()
        model.addAttribute("listBakery",listBakery)

        return "MinMaxStock"
    }

    @GetMapping("/editminmaxstock/{id}")
    fun editMinMaxStock(@PathVariable(name = "id") id: Int, model: Model):String{
        var minMaxStockForm = minMaxStockService.loadEditForm(id)
        model.addAttribute("minMaxStockForm",minMaxStockForm)
        return "EditMinMaxStock"
    }

    @PostMapping("/updateMinMaxStock")
    fun updateMinMaxStock(@Valid minMaxStockForm: MinMaxStockForm, model: Model):String{
        minMaxStockService.updateMinMaxStock(minMaxStockForm)
        return "redirect:/minmaxstock?success"
    }
}