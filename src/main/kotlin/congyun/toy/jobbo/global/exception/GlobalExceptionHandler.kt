package congyun.toy.jobbo.global.exception

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
class GlobalExceptionHandler {
    @ExceptionHandler(JobboException::class)
    fun handleJobboException(ex: JobboException): ResponseEntity<Map<String, Any>> {
        return ResponseEntity.status(ex.error.status)
            .body(mapOf("message" to (ex.message ?: "오류 발생")))
    }

    @ExceptionHandler(MethodArgumentNotValidException::class)
    fun handleValidation(ex: MethodArgumentNotValidException): ResponseEntity<Map<String, Any>> {
        val message = ex.bindingResult.fieldErrors.firstOrNull()?.defaultMessage ?: "유효성 검사 실패"
        return ResponseEntity.badRequest().body(mapOf("message" to message))
    }

    @ExceptionHandler(Exception::class)
    fun handleException(ex: Exception): ResponseEntity<Map<String, Any>> {
        return ResponseEntity.internalServerError()
            .body(mapOf("message" to "서버내부오류"))
    }
}
