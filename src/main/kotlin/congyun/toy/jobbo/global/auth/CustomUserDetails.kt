package congyun.toy.jobbo.global.auth

import congyun.toy.jobbo.domain.user.entity.UserEntity
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails

class CustomUserDetails private constructor(
    val userId: Long,
    private val email: String?,
    private val password: String?,
) : UserDetails {

    companion object {
        fun from(user: UserEntity): CustomUserDetails =
            CustomUserDetails(user.id, user.email, user.password)

        fun fromToken(userId: Long): CustomUserDetails =
            CustomUserDetails(userId, null, null)
    }

    override fun getAuthorities(): Collection<GrantedAuthority> =
        listOf(SimpleGrantedAuthority("ROLE_USER"))

    override fun getPassword(): String? =
        password

    override fun getUsername(): String? = email

    override fun isAccountNonExpired(): Boolean = true

    override fun isAccountNonLocked(): Boolean = true

    override fun isCredentialsNonExpired(): Boolean = true

    override fun isEnabled(): Boolean = true

}