package congyun.toy.jobbo.domain.user.service

import congyun.toy.jobbo.domain.user.presentation.data.request.SignUpRequest

interface SignUpService {
    fun execute(request: SignUpRequest)
}