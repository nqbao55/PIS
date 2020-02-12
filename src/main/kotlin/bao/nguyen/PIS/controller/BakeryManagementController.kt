package bao.nguyen.PIS.controller

import bao.nguyen.PIS.entity.PisBakery
import bao.nguyen.PIS.form.AddPisBakeryForm
import bao.nguyen.PIS.repository.PisBakeryRepository
import bao.nguyen.PIS.service.BakeryManagementService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.*
import org.springframework.web.servlet.ModelAndView
import org.springframework.web.bind.annotation.ModelAttribute
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RequestMapping




@Controller
class BakeryManagementController {
    @Autowired
    lateinit var bakeryManagementService: BakeryManagementService

    @Autowired
    lateinit var bakeryRepository: PisBakeryRepository

    @GetMapping("/bakerymanagement")
    fun detail(model: Model): String {
        var pisBakery: List<PisBakery> = bakeryManagementService.findAll()
        model.addAttribute("pisBakery", pisBakery)
        return "BakeryManagement"
    }

    @GetMapping("/addnewbakery")
    fun addNew(model: Model): String {
        model.addAttribute("dataForm", AddPisBakeryForm())
        return "NewBakery"
    }

    @PostMapping("addnewbakery")
    fun doAddPis(@Validated @ModelAttribute pisBakery: PisBakery):String{
        bakeryRepository.save(pisBakery)
        return "redirect:/bakerymanagement"
    }

    @RequestMapping("/editbakery/{id}")
    fun editPisBakery(@PathVariable(name = "id") id: Int): ModelAndView? {
        val mav = ModelAndView("EditBakery")
        val pisBakery: PisBakery = bakeryManagementService.get(id)
        mav.addObject("pisBakery", pisBakery)
        return mav
    }

    @PostMapping("/save")
    fun editPis(@Validated @ModelAttribute pisBakery: PisBakery):String{
        bakeryRepository.save(pisBakery)
        return "redirect:/bakerymanagement"
    }
    @RequestMapping("/delete/{id}")
    fun deleteProduct(@PathVariable(name = "id") id : Int): String {
        bakeryManagementService.delete(id)
        return "redirect:/bakerymanagement"
    }
}