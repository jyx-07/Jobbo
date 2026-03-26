package congyun.toy.jobbo.domain.user.service.impl

import congyun.toy.jobbo.domain.user.entity.UserEntity
import congyun.toy.jobbo.domain.user.presentation.data.request.SignUpRequest
import congyun.toy.jobbo.domain.user.repository.UserRepository
import congyun.toy.jobbo.domain.user.service.SignUpService
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class SignUpServiceImpl(
    private val userRepository: UserRepository,
    private val passwordEncoder: BCryptPasswordEncoder,
) : SignUpService {
    @Transactional
    override fun execute(request: SignUpRequest) {
        if (userRepository.existsByEmail(request.email)) {
            throw IllegalArgumentException("이미 사용 중인 이메일입니다.")
        }

        userRepository.save(
            UserEntity(
                name = request.name,
                email = request.email,
                password = passwordEncoder.encode(request.password),
            ),
        )
    }
}
