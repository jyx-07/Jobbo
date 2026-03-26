package congyun.toy.jobbo.domain.user.presentation.controller

import congyun.toy.jobbo.domain.user.presentation.data.request.LoginRequest
import congyun.toy.jobbo.domain.user.presentation.data.request.SignUpRequest
import congyun.toy.jobbo.domain.user.service.LoginService
import congyun.toy.jobbo.domain.user.service.SignUpService
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/user")
class UserController(
    private val signUpService: SignUpService,
    private val loginService: LoginService,
) {
    @PostMapping("/signup")
    fun signUp(
        @Valid @RequestBody request: SignUpRequest,
    ): ResponseEntity<Void> {
        signUpService.execute(request)
        return ResponseEntity.status(HttpStatus.CREATED).build()
    }

    @PostMapping("/login")
    fun login(
        @Valid @RequestBody request: LoginRequest,
    ): ResponseEntity<Void> {
        loginService.execute(request)
        return ResponseEntity.ok().build()
    }
}
