package bao.nguyen.PIS.config

import bao.nguyen.PIS.entity.PisUser
import bao.nguyen.PIS.repository.PisUserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
open class UserService @Autowired constructor(private val userRepository: PisUserRepository) {

    /**
     * find pis user by username
     * @return PisUser
     */
    fun findByUsername(username: String): PisUser = userRepository.findByUsername(username)

}
