package congyun.toy.jobbo.global.exception

import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
class GlobalExceptionHandler {
    private val log = LoggerFactory.getLogger(this::class.java)

    @ExceptionHandler(JobboException::class)
    fun handleJobboException(ex: JobboException): ResponseEntity<ErrorResponse> {
        val response = ErrorResponse(status = ex.error.status, message = ex.error.message)
        return ResponseEntity.status(ex.error.status)
            .body(response)
    }

    @ExceptionHandler(MethodArgumentNotValidException::class)
    fun handleValidation(ex: MethodArgumentNotValidException): ResponseEntity<ErrorResponse> {
        val message = ex.bindingResult.fieldErrors.firstOrNull()?.defaultMessage ?: "유효성 검사 실패"
        val response = ErrorResponse(status = HttpStatus.BAD_REQUEST.value(), message = message)
        return ResponseEntity.badRequest().body(response)
    }

    @ExceptionHandler(Exception::class)
    fun handleException(ex: Exception): ResponseEntity<ErrorResponse> {
        log.error("Unhandled Exception: ", ex)
        val response = ErrorResponse(status = HttpStatus.INTERNAL_SERVER_ERROR.value(), message = "서버내부오류")
        return ResponseEntity.internalServerError()
            .body(response)
    }
}
