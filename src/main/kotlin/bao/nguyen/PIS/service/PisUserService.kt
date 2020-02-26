package bao.nguyen.PIS.service

import bao.nguyen.PIS.common.BaseService
import bao.nguyen.PIS.entity.PisUser
import bao.nguyen.PIS.repository.PisBakeryRepository
import bao.nguyen.PIS.repository.PisSettingRepository
import bao.nguyen.PIS.repository.PisUserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service

@Service
class PisUserService : BaseService() {
    @Autowired
    lateinit var pisUserRepository: PisUserRepository

    @Autowired
    lateinit var pisBakeryRepository: PisBakeryRepository

    @Autowired
    lateinit var passwordEncoder: PasswordEncoder

    fun createNewUser(username : String, password: String, bakeryId: Int){
        var user = PisUser()
        user.username = username
        user.password = passwordEncoder.encode(password)
        user.pisBakery = pisBakeryRepository.findById(bakeryId).get()
        user.role = "USER"
        pisUserRepository.save(user)
    }

    fun findByUsername(username: String):PisUser?{
        return pisUserRepository.findByUsername(username)
    }
}
