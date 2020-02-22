package bao.nguyen.PIS.service

import bao.nguyen.PIS.common.BaseService
import bao.nguyen.PIS.entity.PisBakery
import bao.nguyen.PIS.entity.PisCake
import bao.nguyen.PIS.entity.PisRequest
import bao.nguyen.PIS.repository.PisBakeryRepository
import bao.nguyen.PIS.repository.PisRequestRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Sort
import org.springframework.stereotype.Service

@Service
class RequestManagementService : BaseService() {
    @Autowired
    lateinit var pisRequestRepository: PisRequestRepository

    fun getListRequest(): List<PisRequest> {
        return pisRequestRepository.findAll().sortedByDescending { it.createAt }
    }

}