package congyun.toy.jobbo.domain.user.service.impl

import congyun.toy.jobbo.domain.user.presentation.data.request.LoginRequest
import congyun.toy.jobbo.domain.user.repository.UserRepository
import congyun.toy.jobbo.domain.user.service.LoginService
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class LoginServiceImpl(
    private val userRepository: UserRepository,
    private val passwordEncoder: BCryptPasswordEncoder
) : LoginService {

    @Transactional(readOnly = true)
    override fun execute(request: LoginRequest) {
        val user = userRepository.findByEmail(request.email)
            ?: throw IllegalArgumentException("존재하지 않는 이메일입니다.")

        require(passwordEncoder.matches(request.password, user.password)) {
            "비밀번호가 올바르지 않습니다."
        }
    }
}