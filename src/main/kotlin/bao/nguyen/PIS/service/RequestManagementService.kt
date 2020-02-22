package bao.nguyen.PIS.service

import bao.nguyen.PIS.common.BaseService
import bao.nguyen.PIS.entity.PisBakery
import bao.nguyen.PIS.entity.PisCake
import bao.nguyen.PIS.entity.PisRequest
import bao.nguyen.PIS.entity.PisUser
import bao.nguyen.PIS.form.PisRequestForm
import bao.nguyen.PIS.repository.PisBakeryRepository
import bao.nguyen.PIS.repository.PisRequestRepository
import bao.nguyen.PIS.repository.PisUserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Sort
import org.springframework.security.authentication.AnonymousAuthenticationToken
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.stereotype.Service
import java.util.*

@Service
class RequestManagementService : BaseService() {
    @Autowired
    lateinit var pisRequestRepository: PisRequestRepository

    @Autowired
    lateinit var pisUserRepository: PisUserRepository

    fun getListRequest(): List<PisRequest> {
        return pisRequestRepository.findAll().sortedByDescending { it.createAt }
    }

    fun get(id: Int): PisRequest {
        return pisRequestRepository.findById(id).get()
    }

    fun save(pisRequest: PisRequest){
        if (pisRequest.pisBakery == null){
            val userDetails = SecurityContextHolder.getContext().authentication.principal as UserDetails
            var user: PisUser = pisUserRepository.findByUsername(userDetails.username)
            pisRequest.pisBakery = user.pisBakery
        }
        if (pisRequest.createAt == null)
            pisRequest.createAt = Date()
        pisRequestRepository.save(pisRequest)
    }

}