package bao.nguyen.PIS.service

import bao.nguyen.PIS.common.BaseService
import bao.nguyen.PIS.entity.PisBakery
import bao.nguyen.PIS.entity.PisCake
import bao.nguyen.PIS.entity.PisDelivery
import bao.nguyen.PIS.entity.PisDeliveryDetail
import bao.nguyen.PIS.form.DeliveryForm
import bao.nguyen.PIS.form.HomeForm
import bao.nguyen.PIS.repository.PisDeliveryDetailRepository
import bao.nguyen.PIS.repository.PisDeliveryRepository
import bao.nguyen.PIS.repository.PisStoreRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.*
import kotlin.math.round

@Service
class HomeService: BaseService() {

    @Autowired
    lateinit var storeService: StoreService

    @Autowired
    lateinit var storeRepository: PisStoreRepository

    @Autowired
    lateinit var deliveryRepository: PisDeliveryRepository

    @Autowired
    lateinit var deliveryDetailRepository: PisDeliveryDetailRepository

    fun getStatus():Map<PisCake,List<HomeForm>>{
        var listForm = mutableListOf<HomeForm>()
        var listStock = storeService.getListStore()
        for (listStock in listStock!!.values){
            for (stock in listStock){
                var form = HomeForm()
                form.cake = stock.pisCake!!
                form.bakery = stock.pisBakery!!

                var dailySale = stock.pisCake!!.listOfPisDailySale.find { it.pisBakery!!.getId() == stock.pisBakery!!.getId() }
                form.dailySale = dailySale!!.piece
                var setting = stock.pisCake!!.listOfPisSetting.find { it.pisBakery!!.getId() == stock.pisBakery!!.getId() }
                form.minStock = setting!!.minStock!!
                form.maxStock = setting!!.maxStock!!
                form.currentStock = stock.piece
                form.current = (stock.piece/form.dailySale).toFloat()
                form.miss = ((form.maxStock * form.dailySale) - form.currentStock)/form.dailySale

                listForm.add(form)
            }
        }
        return listForm.groupBy { it.cake }
    }

    fun saveData(listId: List<String>):Int{
        var delivery = saveDelivery()
        for (strId in listId){
            // get Bakery
            var bakery = bakeryRepository.findById(strId.toInt()).get()
            //
            var listCake = getListCake()
            for (cake in listCake){
                savePrintDetailWithBakeryAndCake(bakery,cake,delivery)
            }
        }
        return delivery.getId()!!
    }
    fun savePrintDetailWithBakeryAndCake(bakery:PisBakery, cake:PisCake, delivery: PisDelivery){
        var currentStock = findStock(cake,bakery).piece
        var setting = findSetting(cake, bakery)
        var dailySale = findDailySale(cake,bakery)
        val piece = (setting.maxStock!!.toInt() * dailySale) - currentStock
        val tray = (piece/cake.piece_on_tray!!).toFloat()
        return saveDeliveryDetail(delivery,bakery,cake, piece,tray)
    }
    fun saveDelivery():PisDelivery{
        var pisDelivery = PisDelivery()
        pisDelivery.createAt = Date()
        deliveryRepository.save(pisDelivery)
        return pisDelivery
    }

    fun saveDeliveryDetail(delivery: PisDelivery, bakery: PisBakery, cake: PisCake, piece: Int, tray: Float){
        var pisDeliveryDetail = PisDeliveryDetail()
        pisDeliveryDetail.pisDelivery = delivery
        pisDeliveryDetail.createAt = Date()
        pisDeliveryDetail.piece = piece
        pisDeliveryDetail.pisBakery = bakery
        pisDeliveryDetail.pisCake = cake
        pisDeliveryDetail.tray = tray

        deliveryDetailRepository.save(pisDeliveryDetail)

        // Update stock of this Cake
        var stock = findStock(cake, bakery)
        stock.piece += piece
        storeRepository.save(stock)
    }

}