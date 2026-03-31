package congyun.toy.jobbo.global.exception

enum class ErrorCode(
    val status: Int,
    val message: String,
) {
    INVALID_TOKEN(401, "유효하지 않은 토큰입니다."),
    USER_NOT_FOUND(404, "존재하지 않는 유저입니다."),
    EMAIL_ALREADY_EXISTS(409, "이미 사용 중인 이메일입니다."),
    INVALID_PASSWORD(401, "비밀번호가 올바르지 않습니다."),
}
