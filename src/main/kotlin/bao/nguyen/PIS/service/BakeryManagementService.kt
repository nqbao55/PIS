package bao.nguyen.PIS.service

import bao.nguyen.PIS.entity.PisBakery
import bao.nguyen.PIS.form.PisBakeryForm
import bao.nguyen.PIS.repository.PisBakeryRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class BakeryManagementService {
    @Autowired
    lateinit var pisBakeryRepository: PisBakeryRepository

    fun findAll():List<PisBakery>
    {
        return pisBakeryRepository.findAll()
    }

    fun get(id: Int): PisBakery {
        return pisBakeryRepository.findById(id).get()
    }

    fun delete(id: Int)
    {
        return pisBakeryRepository.deleteById(id)
    }

    fun intBakeryFormBy(bakeryId:Int):PisBakeryForm{
        var form = PisBakeryForm()
        val pisBakery: PisBakery = get(bakeryId)
        form.id = bakeryId
        form.name = pisBakery.name
        form.address = pisBakery.address.toString()
        form.email = pisBakery.email.toString()
        form.phone = pisBakery.phone.toString()
        form.username = pisBakery.username.toString()
        return form
    }

    fun updateBakery(form:PisBakeryForm){
        var bakery = get(form.id)
        bakery.name = form.name
        bakery.address = form.address
        bakery.email = form.email
        bakery.phone = form.phone
        bakery.username = form.username

        pisBakeryRepository.save(bakery)
    }
}