package bao.nguyen.PIS.controller

import bao.nguyen.PIS.common.BaseController
import bao.nguyen.PIS.form.DailySaleForm
import bao.nguyen.PIS.service.DailySaleService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import javax.validation.Valid

@Controller
class DailySaleController : BaseController() {
    @Autowired
    lateinit var dailySaleService: DailySaleService

    @GetMapping("/dailysale")
    fun dailySale(model: Model): String {
        // get list dailySale
        val dailySale = dailySaleService.getListDailySale()
        model.addAttribute("dailySale",dailySale)

        // get list bakery
        val listBakery = dailySaleService.getListBakery()
        model.addAttribute("listBakery",listBakery)
        return "DailySale"
    }

    @GetMapping("/editdailysale/{id}")
    fun editDailySale(@PathVariable(name = "id") id: Int, model: Model):String{

        var dailySaleForm = dailySaleService.loadEditForm(id)
        model.addAttribute("dailySaleForm",dailySaleForm)
        return "EditDailySale"
    }

    @PostMapping("/updateDailySale")
    fun updateDailySale(@Valid dailySaleForm: DailySaleForm, model: Model):String{
        dailySaleService.updateDailySale(dailySaleForm)
        return "redirect:/dailysale?success"
    }
}