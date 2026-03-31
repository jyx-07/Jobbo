package congyun.toy.jobbo.domain.auth.presentation.data.request

import jakarta.validation.constraints.Email
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.Size

data class SignUpRequest(
    @field:NotBlank(message = "이름을 입력해주세요.")
    val name: String,
    @field:NotBlank(message = "이메일을 입력해주세요.")
    @field:Email(message = "올바른 이메일 형식이 아닙니다.")
    val email: String,
    @field:NotBlank(message = "비밀번호를 입력해주세요.")
    @field:Size(min = 8, message = "비밀번호는 최소 8자 이상이어야 합니다.")
    val password: String,
)
