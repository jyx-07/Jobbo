package congyun.toy.jobbo.global.exception

import java.lang.RuntimeException

class JobboException(
    val error: ErrorCode,
) : RuntimeException(error.message)
