package bao.nguyen.PIS.service

import bao.nguyen.PIS.entity.PisCake
import bao.nguyen.PIS.form.PisCakeForm
import bao.nguyen.PIS.repository.PisCakeRepository
import bao.nguyen.PIS.repository.PisStoreRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class CakeManagementService {
    @Autowired
    lateinit var pisCakeRepository: PisCakeRepository

    @Autowired
    lateinit var storeService: StoreService

    @Autowired
    lateinit var minMaxStockService: MinMaxStockService

    @Autowired
    lateinit var dailySaleService: DailySaleService

    fun findAll(): List<PisCake> {
        return pisCakeRepository.findAll()
    }

    fun delete(id: Int) {
        return pisCakeRepository.deleteById(id)
    }

    fun save(pisCake: PisCake)
    {
        // Step 1: Save new Cake
        pisCakeRepository.save(pisCake)

        // Step 2: Add the Stock to this Bakery
        storeService.initStoreForNewCake(pisCake.getId()!!)

        // Step 3: Init min & max stock setting
        minMaxStockService.initSettingForCake(pisCake.getId()!!)

        // Step 4: Init daily sale
        dailySaleService.initDailySaleForCake(pisCake.getId()!!)

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