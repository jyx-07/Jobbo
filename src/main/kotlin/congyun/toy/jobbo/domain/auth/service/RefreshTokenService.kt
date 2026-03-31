package congyun.toy.jobbo.domain.auth.service

import congyun.toy.jobbo.domain.auth.presentation.data.response.TokenResponse

interface RefreshTokenService {
    fun execute(refreshToken: String): TokenResponse
}
