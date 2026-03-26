package congyun.toy.jobbo.domain.company.entity

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Table

/**
 * 기업 정보를 나타내는 엔티티.
 *
 * @property id 기업 고유 식별자 (자동 생성)
 * @property companyName 기업명
 * @property description 기업 소개
 * @property companyLocation 기업 위치
 */
@Entity
@Table(name = "company_tb")
class CompanyEntity(
    @field:Id
    @field:GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,
    @field:Column(name = "company_name", nullable = false)
    val companyName: String,
    @field:Column(name = "description", nullable = false)
    val description: String,
    @field:Column(name = "company_location", nullable = false)
    val companyLocation: String,
)
