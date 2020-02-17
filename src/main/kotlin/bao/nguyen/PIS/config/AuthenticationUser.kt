package bao.nguyen.PIS.config

import bao.nguyen.PIS.entity.PisUser
import org.springframework.security.core.authority.AuthorityUtils
import org.springframework.security.core.userdetails.User

class AuthenticationUser(pisUser: PisUser) : User(
        pisUser.username,
        pisUser.password,
        true,
        true,
        true,
        true,
        AuthorityUtils.createAuthorityList("USER")
) {
    val userId: Int = pisUser.getId()!!
}