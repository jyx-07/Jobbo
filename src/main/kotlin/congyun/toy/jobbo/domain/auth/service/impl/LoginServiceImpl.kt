package congyun.toy.jobbo.domain.auth.service.impl

import congyun.toy.jobbo.domain.auth.entity.RefreshToken
import congyun.toy.jobbo.domain.auth.exception.InvalidPasswordException
import congyun.toy.jobbo.domain.auth.exception.UserNotFoundException
import congyun.toy.jobbo.domain.auth.presentation.data.request.LoginRequest
import congyun.toy.jobbo.domain.auth.presentation.data.response.TokenResponse
import congyun.toy.jobbo.domain.auth.repository.RefreshTokenRepository
import congyun.toy.jobbo.domain.auth.service.LoginService
import congyun.toy.jobbo.domain.user.repository.UserRepository
import congyun.toy.jobbo.global.jwt.JwtProperties
import congyun.toy.jobbo.global.jwt.JwtProvider
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class LoginServiceImpl(
    private val userRepository: UserRepository,
    private val passwordEncoder: PasswordEncoder,
    private val jwtProvider: JwtProvider,
    private val jwtProperties: JwtProperties,
    private val refreshTokenRepository: RefreshTokenRepository,
) : LoginService {
    @Transactional(readOnly = true)
    override fun execute(request: LoginRequest): TokenResponse {
        val user =
            userRepository.findByEmail(request.email)
                ?: throw UserNotFoundException()

        if (!passwordEncoder.matches(request.password, user.password)) {
            throw InvalidPasswordException()
        }

        val tokenResponse = jwtProvider.receiveToken(user.id)

        refreshTokenRepository.save(
            RefreshToken(
                userId = user.id.toString(),
                token = tokenResponse.refreshToken,
                expiresIn = jwtProperties.refreshTokenExpiration,
            ),
        )

        return tokenResponse
    }
}
