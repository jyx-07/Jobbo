package congyun.toy.jobbo.global.auth

import congyun.toy.jobbo.domain.auth.exception.UserNotFoundException
import congyun.toy.jobbo.domain.user.repository.UserRepository
import org.springframework.stereotype.Service

@Service
class CustomUserDetailsService(
    private val userRepository: UserRepository,
) {
    fun loadUserByUsername(userId: Long): CustomUserDetails {
        return userRepository.findById(userId)
            .map { CustomUserDetails.from(it) }
            .orElseThrow { UserNotFoundException() }
    }
}
