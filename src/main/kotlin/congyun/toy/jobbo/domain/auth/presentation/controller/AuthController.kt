package congyun.toy.jobbo.domain.auth.presentation.controller

import congyun.toy.jobbo.domain.auth.presentation.data.request.LoginRequest
import congyun.toy.jobbo.domain.auth.presentation.data.request.RefreshRequest
import congyun.toy.jobbo.domain.auth.presentation.data.request.SignUpRequest
import congyun.toy.jobbo.domain.auth.presentation.data.response.TokenResponse
import congyun.toy.jobbo.domain.auth.service.LoginService
import congyun.toy.jobbo.domain.auth.service.RefreshTokenService
import congyun.toy.jobbo.domain.auth.service.SignUpService
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/auth")
class AuthController(
    private val signUpService: SignUpService,
    private val loginService: LoginService,
    private val refreshTokenService: RefreshTokenService,
) {
    @PostMapping("/signup")
    fun signUp(
        @RequestBody @Valid request: SignUpRequest,
    ): ResponseEntity<Void> {
        signUpService.execute(request)
        return ResponseEntity.status(HttpStatus.CREATED).build()
    }

    @PostMapping("/login")
    fun login(
        @RequestBody @Valid request: LoginRequest,
    ): ResponseEntity<TokenResponse> {
        return ResponseEntity.ok(loginService.execute(request))
    }

    @PostMapping("/refresh")
    fun refresh(
        @RequestBody @Valid request: RefreshRequest,
    ): ResponseEntity<TokenResponse> {
        return ResponseEntity.ok(refreshTokenService.execute(request.refreshToken))
    }
}
