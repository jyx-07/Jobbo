package congyun.toy.jobbo

import congyun.toy.jobbo.global.jwt.JwtProperties
import congyun.toy.jobbo.global.security.properties.CorsProperties
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.boot.runApplication

@SpringBootApplication
@EnableConfigurationProperties(JwtProperties::class, CorsProperties::class)
class JobboApplication

fun main(args: Array<String>) {
    runApplication<JobboApplication>(*args)
}
