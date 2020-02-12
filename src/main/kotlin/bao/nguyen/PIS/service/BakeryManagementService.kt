package bao.nguyen.PIS.service

import bao.nguyen.PIS.entity.PisBakery
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
}