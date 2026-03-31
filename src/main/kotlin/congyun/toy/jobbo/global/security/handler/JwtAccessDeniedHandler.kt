package congyun.toy.jobbo.global.security.handler

import com.fasterxml.jackson.databind.ObjectMapper
import congyun.toy.jobbo.global.exception.ErrorResponse
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.http.HttpStatus
import org.springframework.security.access.AccessDeniedException
import org.springframework.security.web.access.AccessDeniedHandler
import org.springframework.stereotype.Component

@Component
class JwtAccessDeniedHandler(
    private val objectMapper: ObjectMapper,
) : AccessDeniedHandler {
    override fun handle(
        request: HttpServletRequest?,
        response: HttpServletResponse?,
        accessDeniedException: AccessDeniedException?,
    ) {
        response?.let {
            it.status = HttpStatus.FORBIDDEN.value()
            it.contentType = "application/json"
            it.characterEncoding = "UTF-8"
        }
        response?.let {
            objectMapper.writeValue(
                it.writer,
                ErrorResponse(
                    403,
                    "접근 권한이 없습니다.",
                ),
            )
        }
    }
}
