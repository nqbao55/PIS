package bao.nguyen.PIS.service

import bao.nguyen.PIS.common.BaseService
import bao.nguyen.PIS.entity.PisCake
import bao.nguyen.PIS.entity.PisSetting
import bao.nguyen.PIS.form.MinMaxStockForm
import bao.nguyen.PIS.repository.PisSettingRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class MinMaxStockService : BaseService(){
    @Autowired
    lateinit var settingRepository: PisSettingRepository

    fun getListSetting():Map<PisCake?,List<PisSetting>>?{
        return  settingRepository.findAll().groupBy { it.pisCake!! }
    }

    fun loadEditForm(cakeId: Int): MinMaxStockForm{
        var listSetting = settingRepository.findByPisCakeIdOrderById(cakeId)
        var form = MinMaxStockForm()
        form.cake = cakeRepository.findById(cakeId).get()
        form.listSetting = listSetting.toMutableList()
        form.listId = form.listSetting.groupBy { it.getId()!! }.keys.toMutableList()
        form.id = form.cake.getId()!!
        return form
    }

    fun updateMinMaxStock(form: MinMaxStockForm){
        // get list Setting
        form.listSetting.forEachIndexed { index, setting ->
            var minMaxStock = settingRepository.findById(form.listId[index]).get()
            minMaxStock.minStock = form.listSetting[index].minStock
            minMaxStock.maxStock = form.listSetting[index].maxStock
            settingRepository.save(minMaxStock)
        }
    }

    fun initSettingForBakery(bakeryId:Int){
        var listCake = getListCake()
        for (cake in listCake){
            var setting = PisSetting()
            setting.pisCake = cakeRepository.findById(cake.getId()!!).get()
            setting.pisBakery = bakeryRepository.findById(bakeryId).get()
            setting.minStock = 0
            setting.maxStock = 0

            settingRepository.save(setting)
        }
    }

    fun initSettingForCake(cakeId:Int){
        var listBakery = getListBakery()
        for (bakery in listBakery){
            var setting = PisSetting()
            setting.pisCake = cakeRepository.findById(cakeId).get()
            setting.pisBakery = bakeryRepository.findById(bakery.getId()!!).get()
            setting.minStock = 1
            setting.maxStock = 2

            settingRepository.save(setting)
        }
    }
}