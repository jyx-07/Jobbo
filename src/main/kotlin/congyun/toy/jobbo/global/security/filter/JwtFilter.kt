package congyun.toy.jobbo.global.security.filter

import com.fasterxml.jackson.databind.ObjectMapper
import congyun.toy.jobbo.global.auth.CustomUserDetails
import congyun.toy.jobbo.global.exception.ErrorCode
import congyun.toy.jobbo.global.exception.ErrorResponse
import congyun.toy.jobbo.global.jwt.JwtProvider
import io.jsonwebtoken.JwtException
import jakarta.servlet.FilterChain
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Component
import org.springframework.web.filter.OncePerRequestFilter

@Component
class JwtFilter(
    private val objectMapper: ObjectMapper,
    private val jwtProvider: JwtProvider,
) : OncePerRequestFilter() {
    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain,
    ) {
        val token = jwtProvider.resolveToken(request)

        if (token != null) {
            try {
                val claims = jwtProvider.getClaims(token)
                if (!jwtProvider.isAccessToken(claims)) {
                    setErrorResponse(response, ErrorCode.INVALID_TOKEN)
                    return
                }
                val userId = jwtProvider.getUserId(claims)
                val userDetails = CustomUserDetails.fromToken(userId)
                val authentication =
                    UsernamePasswordAuthenticationToken(
                        userDetails,
                        null,
                        userDetails.authorities,
                    )
                SecurityContextHolder.getContext().authentication = authentication
            } catch (e: JwtException) {
                setErrorResponse(response, ErrorCode.INVALID_TOKEN)
                return
            }
        }

        filterChain.doFilter(request, response)
    }

    private fun setErrorResponse(
        response: HttpServletResponse,
        error: ErrorCode,
    ) {
        response.status = error.status
        response.contentType = ("application/json; charset=UTF-8")
        objectMapper.writeValue(
            response.writer,
            ErrorResponse(error.status, error.message),
        )
    }
}
