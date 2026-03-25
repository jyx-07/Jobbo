package congyun.toy.jobbo.domain.jobPosting.entity

import congyun.toy.jobbo.domain.company.entity.CompanyEntity
import congyun.toy.jobbo.domain.jobPosting.entity.presentation.data.Status
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.EnumType
import jakarta.persistence.Enumerated
import jakarta.persistence.FetchType
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne
import jakarta.persistence.Table

/**
 * 채용 공고를 나타내는 엔티티.
 *
 * @property id 채용 공고 고유 식별자 (자동 생성)
 * @property title 공고 제목
 * @property description 공고 상세 내용
 * @property position 모집 직무
 * @property location 근무 지역
 * @property salary 급여 (단위: 원)
 * @property deadline 지원 마감일 (Unix timestamp)
 * @property status 공고 상태 ([Status.OPEN] / [Status.CLOSED])
 * @property companyId 공고를 게시한 기업 ([CompanyEntity] 참조)
 */
@Entity
@Table(name = "job_posting")
class JobPostingEntity(
    @field:Id
    @field:GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,

    @field:Column(name = "title", nullable = false)
    val title: String,

    @field:Column(name = "description", nullable = false)
    val description: String,

    @field:Column(name = "position", nullable = false)
    val position: String,

    @field:Column(name = "location", nullable = false)
    val location: String,

    @field:Column(name = "salary", nullable = false)
    val salary: Long,

    @field:Column(name = "deadline", nullable = false)
    val deadline: Long,

    @field:Column(name = "status", nullable = false)
    @field:Enumerated(EnumType.STRING)
    val status: Status,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "company_id", nullable = false)
    val companyId: CompanyEntity,
)
