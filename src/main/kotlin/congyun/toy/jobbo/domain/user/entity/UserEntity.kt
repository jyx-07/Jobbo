package congyun.toy.jobbo.domain.user.entity

import jakarta.persistence.*

/**
 * 사용자 정보를 나타내는 엔티티.
 *
 * @property id 사용자 고유 식별자 (자동 생성)
 * @property name 사용자 이름
 */
@Entity
@Table(name = "user_tb")
class UserEntity(
    @field:Id
    @field:GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,

    @field:Column(name = "name", nullable = false)
    val name: String,


)