package congyun.toy.jobbo.domain.user.entity

import jakarta.persistence.*

/**
 * 사용자 정보를 나타내는 엔티티.
 *
 * @property id 사용자 고유 식별자 (자동 생성)
 * @property name 사용자 이름
 * @property email 사용자 이메일 (로그인 ID로 사용, 중복 불가)
 * @property password 사용자 비밀번호 (암호화된 값 저장)
 */
@Entity
@Table(name = "user_tb")
class UserEntity(
    @field:Id
    @field:GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,

    @field:Column(name = "name", nullable = false)
    val name: String,

    @field:Column(name = "email", nullable = false, unique = true)
    val email: String,

    @field:Column(name = "password", nullable = false)
    val password: String,
)