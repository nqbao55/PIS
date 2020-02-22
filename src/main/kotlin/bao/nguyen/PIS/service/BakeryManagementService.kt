package bao.nguyen.PIS.service

import bao.nguyen.PIS.entity.PisBakery
import bao.nguyen.PIS.entity.PisUser
import bao.nguyen.PIS.form.PisBakeryForm
import bao.nguyen.PIS.repository.PisBakeryRepository
import bao.nguyen.PIS.repository.PisUserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import javax.validation.constraints.Email

@Service
class BakeryManagementService {
    @Autowired
    lateinit var pisBakeryRepository: PisBakeryRepository

    @Autowired
    lateinit var pisUserRepository: PisUserRepository

    @Autowired
    lateinit var pisUserService: PisUserService

    @Autowired
    lateinit var minMaxStockService: MinMaxStockService

    @Autowired
    lateinit var dailySaleService: DailySaleService

    @Autowired
    lateinit var storeService: StoreService

    fun findAll():List<PisBakery>
    {
        return pisBakeryRepository.findAll()
    }

    fun get(id: Int): PisBakery {
        return pisBakeryRepository.findById(id).get()
    }

    fun findByEmail(email:String):PisBakery{
        return pisBakeryRepository.findByEmail(email)
    }

    fun delete(id: Int)
    {
        return pisBakeryRepository.deleteById(id)
    }

    fun addNewBakery(pisBakery: PisBakery):Int{
        // Step 1: Create PisBakery
        if (pisUserRepository.findByUsername(pisBakery.username) != null){
            return 403 // Existed user
        }
        pisBakeryRepository.save(pisBakery)
        // Step 2: Create user for this Bakery
        pisUserService.createNewUser(pisBakery.username, pisBakery.password, pisBakery.getId()!!)

        // Step 3: Add the Stock to this Bakery
        storeService.initStoreForNewBakery(pisBakery.getId()!!)
        // Step 4: Init min & max stock setting
        minMaxStockService.initSettingForBakery(pisBakery.getId()!!)

        // Step 5: Init daily sale
        dailySaleService.initDailySaleForBakery(pisBakery.getId()!!)
        return 200; // OK
    }

    fun intBakeryFormBy(bakeryId:Int):PisBakeryForm{
        var form = PisBakeryForm()
        val pisBakery: PisBakery = get(bakeryId)
        form.id = bakeryId
        form.name = pisBakery.name
        form.address = pisBakery.address.toString()
        form.email = pisBakery.email.toString()
        form.phone = pisBakery.phone.toString()
        form.username = pisBakery.name.toString()
        return form
    }

    fun updateBakery(form:PisBakeryForm){
        var bakery = get(form.id)
        bakery.name = form.name
        bakery.address = form.address
        bakery.email = form.email
        bakery.phone = form.phone
        pisBakeryRepository.save(bakery)
    }
}