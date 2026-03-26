package congyun.toy.jobbo.domain.user.service

import congyun.toy.jobbo.domain.user.presentation.data.request.LoginRequest

interface LoginService {
    fun execute(request: LoginRequest)
}