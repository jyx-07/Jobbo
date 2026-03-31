package congyun.toy.jobbo.global.security.properties

import org.springframework.boot.context.properties.ConfigurationProperties

@ConfigurationProperties(prefix = "cors")
class CorsProperties(
    private val allowedOrigins: List<String>,
)
