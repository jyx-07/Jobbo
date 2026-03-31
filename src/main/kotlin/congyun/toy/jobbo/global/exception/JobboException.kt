package congyun.toy.jobbo.global.exception

import java.lang.RuntimeException

open class JobboException(
    val error: ErrorCode,
) : RuntimeException(error.message)
