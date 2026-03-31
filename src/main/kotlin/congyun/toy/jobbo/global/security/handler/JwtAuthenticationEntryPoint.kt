package congyun.toy.jobbo.global.security.handler

import com.fasterxml.jackson.databind.ObjectMapper
import congyun.toy.jobbo.global.exception.ErrorResponse
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.http.HttpStatus
import org.springframework.security.core.AuthenticationException
import org.springframework.security.web.AuthenticationEntryPoint
import org.springframework.stereotype.Component

@Component
class JwtAuthenticationEntryPoint(
    private val objectMapper: ObjectMapper,
) : AuthenticationEntryPoint {
    override fun commence(
        request: HttpServletRequest?,
        response: HttpServletResponse?,
        authException: AuthenticationException?,
    ) {
        response?.let {
            it.status = HttpStatus.UNAUTHORIZED.value()
            it.contentType = "application/json"
            it.characterEncoding = "UTF-8"
        }
        response?.let {
            objectMapper.writeValue(
                it.writer,
                ErrorResponse(
                    401,
                    "로그인이 되어 있지 않습니다.",
                ),
            )
        }
    }
}
