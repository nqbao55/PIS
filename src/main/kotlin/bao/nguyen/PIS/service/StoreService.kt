package bao.nguyen.PIS.service

import bao.nguyen.PIS.common.BaseService
import bao.nguyen.PIS.entity.PisCake
import bao.nguyen.PIS.entity.PisDailySale
import bao.nguyen.PIS.entity.PisStore
import bao.nguyen.PIS.form.DailySaleForm
import bao.nguyen.PIS.form.StoreForm
import bao.nguyen.PIS.repository.PisStoreRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.*

@Service
class StoreService : BaseService() {
    @Autowired
    lateinit var pisStoreRepository: PisStoreRepository

    fun initStoreForNewBakery(bakeryId: Int){
        var listCake = getListCake()
        for (cake in listCake){
            var store = PisStore()
            store.pisCake = cakeRepository.findById(cake.getId()!!).get()
            store.pisBakery = bakeryRepository.findById(bakeryId).get()
            store.piece = 0

            pisStoreRepository.save(store)
        }
    }
    fun initStoreForNewCake(cakeId: Int){
        var listBakery = getListBakery()
        for (bakery in listBakery){
            var store = PisStore()
            store.pisCake = cakeRepository.findById(cakeId).get()
            store.pisBakery = bakeryRepository.findById(bakery.getId()!!).get()
            store.piece = 0

            pisStoreRepository.save(store)
        }
    }

    fun getListStore():Map<PisCake?,List<PisStore>>?{
        return pisStoreRepository.findAll().groupBy { it.pisCake }
    }

    fun initStoreForm(bakeryId: Int):StoreForm{
        var form = StoreForm()
        form.bakery = bakeryRepository.findById(bakeryId).get()
        form.listStore = pisStoreRepository.findByPisBakeryIdOrderById(bakeryId).toMutableList()
        form.listId = form.listStore.groupBy { it.getId()!! }.keys.toMutableList()
        form.id = form.bakery.getId()!!
        return form
    }

    fun updateStore(form: StoreForm){
        // get list DailySale
        form.listStore.forEachIndexed { index, data ->
            var store = pisStoreRepository.findById(form.listId[index]).get()
            store.piece = form.listStore[index].piece
            pisStoreRepository.save(store)
        }
    }

    fun dailyUpdateStore(){
        val current = localDateTimeToString(dateToLocalDateTime(Date())!!,"yyyy-MM-dd")
        val listStore = pisStoreRepository.findAll()
        for (store in listStore){
            var dailyUpdate : String = ""
            if (store.dailyUpdate != null){
                dailyUpdate = localDateTimeToString(dateToLocalDateTime(store.dailyUpdate)!!,"yyyy-MM-dd")
            }
            if (current != dailyUpdate){
                val dailySale = findDailySale(store.pisCake!!, store.pisBakery!!)
                store.piece -= dailySale
                if (store.piece < 0)
                    store.piece = 0
                store.dailyUpdate = Date()
                store.updateAt = Date()
                pisStoreRepository.save(store)
            }

        }
    }
}