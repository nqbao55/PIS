package bao.nguyen.PIS.common

import bao.nguyen.PIS.entity.PisUser
import bao.nguyen.PIS.service.PisUserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.authentication.AnonymousAuthenticationToken
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.ModelAttribute

abstract class BaseController  {

    @Autowired
    lateinit var pisUserService: PisUserService

    lateinit var loggedUser: PisUser

    @ModelAttribute
    fun addAttributes(model: Model) {
        if (SecurityContextHolder.getContext().authentication != null &&
                SecurityContextHolder.getContext().authentication.isAuthenticated &&
                //when Anonymous AuthenticationRequest is enabled
                SecurityContextHolder.getContext().authentication !is AnonymousAuthenticationToken) {
            // check is CusUser or SupUser
            val userDetails = SecurityContextHolder.getContext().authentication.principal as UserDetails
            loggedUser = pisUserService.findByUsername(userDetails.username)
            model.addAttribute("user_role",loggedUser.role)
            if (loggedUser.role != "ADMIN")
                model.addAttribute("bakeryName",loggedUser.pisBakery!!.name)
            else
                model.addAttribute("bakeryName","Admin")
        }
    }
}