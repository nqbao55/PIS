package bao.nguyen.PIS.config

import bao.nguyen.PIS.entity.PisUser
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Component

@Component
open class UserDetailsServiceImpl : UserDetailsService {

    @Autowired
    lateinit var userService: UserService

    override fun loadUserByUsername(userName : String ) : UserDetails {
        var user : PisUser?  = null
        try {
            user = userService.findByUsername(userName)
        } catch (e:Exception ) {
            throw UsernameNotFoundException("can't get User")
        }

        if(user == null) {
            throw UsernameNotFoundException("User does not exists")
        }

        return AuthenticationUser(user)
    }

}