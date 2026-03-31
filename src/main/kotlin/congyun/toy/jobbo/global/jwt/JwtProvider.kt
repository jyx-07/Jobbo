package congyun.toy.jobbo.global.jwt

import congyun.toy.jobbo.domain.auth.presentation.data.response.TokenResponse
import congyun.toy.jobbo.global.config.logger
import io.jsonwebtoken.Claims
import io.jsonwebtoken.ExpiredJwtException
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.UnsupportedJwtException
import io.jsonwebtoken.security.Keys
import jakarta.annotation.PostConstruct
import jakarta.servlet.http.HttpServletRequest
import org.springframework.stereotype.Component
import org.springframework.util.StringUtils
import java.io.UnsupportedEncodingException
import java.nio.charset.StandardCharsets
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.ZoneOffset
import java.util.Date
import javax.crypto.SecretKey

@Component
class JwtProvider(
    private val jwtProperties: JwtProperties,
) {
    private lateinit var secretKey: SecretKey

    companion object {
        private const val TOKEN_TYPE = "type"
        private const val ACCESS_TOKEN = "accessToken"
        private const val REFRESH_TOKEN = "refreshToken"
        private const val BEARER_PREFIX = "Bearer "
        private const val AUTHORIZATION_HEADER = "Authorization"
    }

    @PostConstruct
    fun init() {
        secretKey = Keys.hmacShaKeyFor(jwtProperties.secret.toByteArray(StandardCharsets.UTF_8))
    }

    fun receiveToken(userId: Long): TokenResponse {
        val accessExpiryDate = calculateExpiryDate(jwtProperties.accessTokenExpiration)
        val refreshExpiryDate = calculateExpiryDate(jwtProperties.refreshTokenExpiration)

        return TokenResponse(
            accessToken = createToken(userId, ACCESS_TOKEN, accessExpiryDate),
            accessTokenExpiresAt = toLocalDate(accessExpiryDate),
            refreshToken = createToken(userId, REFRESH_TOKEN, refreshExpiryDate),
            refreshTokenExpiresAt = toLocalDate(refreshExpiryDate),
        )
    }

    fun generateAccessToken(userId: Long): String {
        val expiryDate = calculateExpiryDate(jwtProperties.accessTokenExpiration)
        return createToken(userId, ACCESS_TOKEN, expiryDate)
    }

    fun generateRefreshToken(userId: Long): String {
        val expiryDate = calculateExpiryDate(jwtProperties.refreshTokenExpiration)
        return createToken(userId, REFRESH_TOKEN, expiryDate)
    }

    fun validateToken(token: String): Boolean =
        runCatching {
            Jwts.parser().verifyWith(secretKey).build().parseSignedClaims(token)
        }.onFailure { e ->
            when (e) {
                is SecurityException -> logger().error("잘못된 JWT 서명입니다.")
                is ExpiredJwtException -> logger().error("만료된 JWT 토큰입니다.")
                is UnsupportedJwtException -> logger().error("지원하지 않는 JWT 토큰입니다.")
                is IllegalArgumentException -> logger().error("JWT 토큰이 잘못되었습니다")
            }
        }.isSuccess

    fun getClaims(token: String): Claims {
        return Jwts.parser()
            .verifyWith(secretKey)
            .build()
            .parseSignedClaims(token)
            .payload
    }

    fun isAccessToken(claims: Claims): Boolean {
        return ACCESS_TOKEN == claims.get(TOKEN_TYPE, String::class.java)
    }

    fun isRefreshToken(claims: Claims): Boolean {
        return REFRESH_TOKEN == claims.get(TOKEN_TYPE, String::class.java)
    }

    fun resolveToken(request: HttpServletRequest): String? {
        val bearerToken = request.getHeader(AUTHORIZATION_HEADER)
        if (StringUtils.hasText(bearerToken) && bearerToken.startsWith(BEARER_PREFIX)) {
            return bearerToken.substring(BEARER_PREFIX.length)
        }
        return null
    }

    fun getUserId(claims: Claims): Long = claims.subject.toLong()

    private fun createToken(
        userId: Long,
        type: String,
        expiryDate: Date,
    ): String {
        return Jwts.builder()
            .subject(userId.toString())
            .claim(TOKEN_TYPE, type)
            .issuedAt(Date())
            .expiration(expiryDate)
            .signWith(secretKey, Jwts.SIG.HS256)
            .compact()
    }

    private fun toLocalDate(date: Date): LocalDateTime {
        return date.toInstant()
            .atZone(ZoneOffset.UTC)
            .toLocalDateTime()
    }

    private fun calculateExpiryDate(validitySeconds: Long): Date = Date(System.currentTimeMillis() + validitySeconds * 1000L)
}
