package congyun.toy.jobbo.domain.auth.service

import congyun.toy.jobbo.domain.auth.presentation.data.request.LoginRequest
import congyun.toy.jobbo.domain.auth.presentation.data.response.TokenResponse

interface LoginService {
    fun execute(request: LoginRequest): TokenResponse
}
