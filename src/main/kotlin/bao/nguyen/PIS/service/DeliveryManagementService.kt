package bao.nguyen.PIS.service

import bao.nguyen.PIS.common.BaseService
import bao.nguyen.PIS.entity.PisDelivery
import bao.nguyen.PIS.repository.PisDeliveryRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class DeliveryManagementService : BaseService() {
    @Autowired
    lateinit var pisDeliveryRepository: PisDeliveryRepository

    fun findAll():List<PisDelivery>{
       return pisDeliveryRepository.findAll()
    }
}