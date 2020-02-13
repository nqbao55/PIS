package bao.nguyen.PIS.service

import bao.nguyen.PIS.entity.PisCake
import bao.nguyen.PIS.form.PisCakeForm
import bao.nguyen.PIS.repository.PisCakeRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class CakeManagementService {
    @Autowired
    lateinit var pisCakeRepository: PisCakeRepository

    fun findAll(): List<PisCake> {
        return pisCakeRepository.findAll()
    }

    fun delete(id: Int) {
        return pisCakeRepository.deleteById(id)
    }

    fun save(pisCake: PisCake)
    {
        pisCakeRepository.save(pisCake)
    }
    fun get(id: Int): PisCake {
        return pisCakeRepository.findById(id).get()
    }

    fun intCakeFormBy(cakeId: Int): PisCakeForm {
        var form = PisCakeForm()
        val pisCake: PisCake = get(cakeId)
        form.id = cakeId
        form.name = pisCake.name
        form.price = pisCake.price.toString()
        return form
    }

    fun updateCake(form: PisCakeForm) {
        var cake = get(form.id)
        cake.name = form.name
        cake.price = form.price.toInt()

        pisCakeRepository.save(cake)
    }
}