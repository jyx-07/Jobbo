package congyun.toy.jobbo.domain.auth.repository

import congyun.toy.jobbo.domain.auth.entity.RefreshToken
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface RefreshTokenRepository : CrudRepository<RefreshToken, String> {
    fun findByToken(token: String): RefreshToken?
}
