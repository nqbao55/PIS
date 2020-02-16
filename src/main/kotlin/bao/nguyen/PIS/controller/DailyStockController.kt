package bao.nguyen.PIS.controller

import bao.nguyen.PIS.form.DailyStockForm
import bao.nguyen.PIS.form.PisBakeryForm
import bao.nguyen.PIS.service.BakeryManagementService
import bao.nguyen.PIS.service.DailyStockService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import javax.validation.Valid

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

    @GetMapping("/editdailystock/{id}")
    fun editDailyStock(@PathVariable(name = "id") id: Int,model: Model):String{

        var dailyStockForm = dailyStockService.loadEditForm(id)
        model.addAttribute("dailyStockForm",dailyStockForm)
        return "EditDailyStock"
    }

    @PostMapping("/updateDailyStock")
    fun updateDailyStock(@Valid dailyStockForm: DailyStockForm, model: Model):String{
        dailyStockService.updateDailyStock(dailyStockForm)
        return "redirect:/dailystock?success"
    }
}