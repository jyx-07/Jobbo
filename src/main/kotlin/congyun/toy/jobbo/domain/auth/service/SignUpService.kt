package congyun.toy.jobbo.domain.auth.service

import congyun.toy.jobbo.domain.auth.presentation.data.request.SignUpRequest

interface SignUpService {
    fun execute(request: SignUpRequest)
}
