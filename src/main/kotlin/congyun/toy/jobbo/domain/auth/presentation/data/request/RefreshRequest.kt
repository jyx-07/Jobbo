package congyun.toy.jobbo.domain.auth.presentation.data.request

import jakarta.validation.constraints.NotBlank

data class RefreshRequest(
    @field:NotBlank(message = "리프레시 토큰을 입력해주세요.")
    val refreshToken: String,
)
