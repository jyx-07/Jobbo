package congyun.toy.jobbo.domain.auth.presentation.data.response

import com.fasterxml.jackson.annotation.JsonFormat
import java.time.LocalDateTime

data class TokenResponse(
    val accessToken: String,
    @field:JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss")
    val accessTokenExpiresAt: LocalDateTime,
    val refreshToken: String,
    @field:JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss")
    val refreshTokenExpiresAt: LocalDateTime,
)
