package bao.nguyen.PIS.controller

import bao.nguyen.PIS.common.BaseController
import bao.nguyen.PIS.entity.PisCake
import bao.nguyen.PIS.form.PisCakeForm
import bao.nguyen.PIS.service.CakeManagementService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.*
import javax.validation.Valid

@Controller
class CakeManagementController : BaseController(){
    @Autowired
    lateinit var cakeManagementService: CakeManagementService

    @GetMapping("/cakemanagement")
    fun detail(model: Model): String {
        var pisCake: List<PisCake> = cakeManagementService.findAll()
        model.addAttribute("pisCake", pisCake)
        return "CakeManagement"
    }

    @GetMapping("/addnewcake")
    fun addNew(model: Model): String {
        model.addAttribute("dataForm", PisCakeForm())
        return "NewCake"
    }

    @PostMapping("addnewcake")
    fun doAddPis(@Validated @ModelAttribute pisCake: PisCake):String{
        cakeManagementService.save(pisCake)
        return "redirect:/cakemanagement"
    }


    @RequestMapping("/editcake/{id}")
    fun editPisCake(@PathVariable(name = "id") id: Int, model: Model): String {
        var cakeForm = cakeManagementService.intCakeFormBy(id)
        model.addAttribute("cakeForm",cakeForm)
        return "EditCake"
    }

    @PostMapping("/savecake")
    fun saveCake(@Valid cakeForm: PisCakeForm, model: Model):String{
        cakeManagementService.updateCake(cakeForm)
        return "redirect:/cakemanagement?success"
    }

    @RequestMapping("/deletecake/{id}")
    fun deleteCake(@PathVariable(name = "id") id : Int): String {
        cakeManagementService.delete(id)
        return "redirect:/cakemanagement"
    }
}