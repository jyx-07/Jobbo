package congyun.toy.jobbo.domain.jobPosting.entity.presentation.data

/**
 * 채용 공고의 상태를 나타내는 열거형.
 *
 * - [OPEN]: 지원 가능한 공개 상태
 * - [CLOSED]: 마감되어 지원 불가능한 상태
 */
enum class Status {
    OPEN,
    CLOSED,
}
