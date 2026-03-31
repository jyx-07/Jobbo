package congyun.toy.jobbo.domain.auth.exception

import congyun.toy.jobbo.global.exception.ErrorCode
import congyun.toy.jobbo.global.exception.JobboException

class InvalidTokenException : JobboException(ErrorCode.INVALID_TOKEN)
