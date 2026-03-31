package congyun.toy.jobbo.domain.auth.service.impl

import congyun.toy.jobbo.domain.auth.exception.EmailAlreadyExistsException
import congyun.toy.jobbo.domain.auth.presentation.data.request.SignUpRequest
import congyun.toy.jobbo.domain.auth.service.SignUpService
import congyun.toy.jobbo.domain.user.entity.UserEntity
import congyun.toy.jobbo.domain.user.repository.UserRepository
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class SignUpServiceImpl(
    private val userRepository: UserRepository,
    private val passwordEncoder: PasswordEncoder,
) : SignUpService {

    @Transactional
    override fun execute(request: SignUpRequest) {
        if (userRepository.existsByEmail(request.email)) {
            throw EmailAlreadyExistsException()
        }
        userRepository.save(
            UserEntity(
                name = request.name,
                email = request.email,
                password = passwordEncoder.encode(request.password),
            )
        )
    }
}
