package congyun.toy.jobbo.domain.auth.service.impl

import congyun.toy.jobbo.domain.auth.entity.RefreshToken
import congyun.toy.jobbo.domain.auth.exception.InvalidTokenException
import congyun.toy.jobbo.domain.auth.presentation.data.response.TokenResponse
import congyun.toy.jobbo.domain.auth.repository.RefreshTokenRepository
import congyun.toy.jobbo.domain.auth.service.RefreshTokenService
import congyun.toy.jobbo.global.jwt.JwtProperties
import congyun.toy.jobbo.global.jwt.JwtProvider
import org.springframework.stereotype.Service

@Service
class RefreshTokenServiceImpl(
    private val jwtProvider: JwtProvider,
    private val jwtProperties: JwtProperties,
    private val refreshTokenRepository: RefreshTokenRepository,
) : RefreshTokenService {
    override fun execute(refreshToken: String): TokenResponse {
        if (!jwtProvider.validateToken(refreshToken)) {
            throw InvalidTokenException()
        }

        val claims = jwtProvider.getClaims(refreshToken)
        if (!jwtProvider.isRefreshToken(claims)) {
            throw InvalidTokenException()
        }

        val storedToken =
            refreshTokenRepository.findByToken(refreshToken)
                ?: throw InvalidTokenException()

        val userId = jwtProvider.getUserId(claims)
        val newTokenResponse = jwtProvider.receiveToken(userId)

        refreshTokenRepository.delete(storedToken)
        refreshTokenRepository.save(
            RefreshToken(
                userId = userId.toString(),
                token = newTokenResponse.refreshToken,
                expiresIn = jwtProperties.refreshTokenExpiration,
            ),
        )

        return newTokenResponse
    }
}
