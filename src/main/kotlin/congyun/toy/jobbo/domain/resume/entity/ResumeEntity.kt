package congyun.toy.jobbo.domain.resume.entity

import congyun.toy.jobbo.domain.resume.presentation.data.Career
import congyun.toy.jobbo.domain.user.entity.UserEntity
import io.hypersistence.utils.hibernate.type.json.JsonBinaryType
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.FetchType
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne
import jakarta.persistence.Table
import org.hibernate.annotations.Type

/**
 * 구직자의 이력서를 나타내는 엔티티.
 *
 * skills, career, education 필드는 PostgreSQL JSONB 타입으로 저장된다.
 *
 * @property id 이력서 고유 식별자 (자동 생성)
 * @property user 이력서 소유자 ([UserEntity] 참조)
 * @property title 이력서 제목
 * @property summary 자기소개 요약
 * @property skills 보유 기술 목록 (JSONB)
 * @property career 경력 목록 (JSONB, [Career] 참조)
 * @property education 학력 목록 (JSONB)
 */
@Entity
@Table(name = "resumes_tb")
class ResumeEntity(
    @field:Id
    @field:GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,
    @ManyToOne(fetch = FetchType.LAZY)
    @field:JoinColumn(name = "user_id", nullable = false)
    var user: UserEntity,
    @field:Column(name = "title", nullable = false)
    var title: String,
    @field:Column(name = "summary", nullable = false)
    var summary: String,
    @field:Type(JsonBinaryType::class)
    @field:Column(columnDefinition = "jsonb")
    val skills: List<String> = emptyList(),
    @field:Type(JsonBinaryType::class)
    @field:Column(columnDefinition = "jsonb")
    val career: List<Career> = emptyList(),
    @field:Type(JsonBinaryType::class)
    @field:Column(columnDefinition = "jsonb")
    val education: List<String> = emptyList(),
)
