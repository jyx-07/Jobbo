package congyun.toy.jobbo.domain.auth.entity

import org.springframework.data.redis.core.index.Indexed
import org.springframework.data.annotation.Id
import org.springframework.data.redis.core.RedisHash
import org.springframework.data.redis.core.TimeToLive
import java.util.concurrent.TimeUnit

@RedisHash(value = "refresh_token")
class RefreshToken(
    @Id
    val userId: String,

    @Indexed
    val token: String,

    @TimeToLive(unit = TimeUnit.SECONDS)
    val expiresIn: Long,

    ) {
}