package bao.nguyen.PIS.service

import bao.nguyen.PIS.common.BaseService
import bao.nguyen.PIS.entity.PisDailySale
import bao.nguyen.PIS.entity.PisStore
import bao.nguyen.PIS.repository.PisStoreRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
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
}