package congyun.toy.jobbo.domain.user.repository

import congyun.toy.jobbo.domain.user.entity.UserEntity
import org.springframework.data.jpa.repository.JpaRepository

interface UserRepository : JpaRepository<UserEntity, Long> {
    fun existsByEmail(email: String): Boolean

    fun findByEmail(email: String): UserEntity?
}
