package congyun.toy.jobbo.domain.resume.presentation.data

/**
 * 이력서에 포함되는 경력 정보를 나타내는 데이터 클래스.
 *
 * @property company 근무한 회사명
 * @property position 담당 직무
 * @property period 근무 기간 (예: "2022.03 ~ 2024.01")
 */
data class Career(
    val company: String,
    val position: String,
    val period: String,
)
