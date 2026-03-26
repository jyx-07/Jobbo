package congyun.toy.jobbo.domain.applications.entity

import congyun.toy.jobbo.domain.jobPosting.entity.JobPostingEntity
import congyun.toy.jobbo.domain.resume.entity.ResumeEntity
import congyun.toy.jobbo.domain.user.entity.UserEntity
import jakarta.persistence.Entity
import jakarta.persistence.FetchType
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne
import jakarta.persistence.Table

/**
 * 채용 지원 내역을 나타내는 엔티티.
 *
 * @property id 지원 고유 식별자 (자동 생성)
 * @property userId 지원한 사용자 ([UserEntity] 참조)
 * @property jobPostingID 지원한 채용 공고 ([JobPostingEntity] 참조)
 * @property resumeID 지원 시 사용한 이력서 ([ResumeEntity] 참조)
 */
@Entity
@Table(name = "application_tb")
class ApplicationEntity(
    @field:Id
    @field:GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,
    @ManyToOne(fetch = FetchType.LAZY)
    @field:JoinColumn(name = "user_id", nullable = false)
    var userId: UserEntity,
    @ManyToOne(fetch = FetchType.LAZY)
    @field:JoinColumn(name = "job_posting_id", nullable = false)
    var jobPostingID: JobPostingEntity,
    @ManyToOne(fetch = FetchType.LAZY)
    @field:JoinColumn(name = "resume_id", nullable = false)
    var resumeID: ResumeEntity,
)
