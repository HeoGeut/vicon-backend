package com.vicon.viconbackend.domain.contest

import com.vicon.viconbackend.domain.apply.Apply
import com.vicon.viconbackend.domain.common.Auditable
import com.vicon.viconbackend.domain.member.Member
import java.math.BigDecimal
import java.time.LocalDateTime
import javax.persistence.*

@Entity
data class Contest(
    @Enumerated(EnumType.STRING)
    var type: ContestType? = ContestType.STANDARD,
    var category: String = "",
    @Enumerated(EnumType.STRING)
    var channelType: ChannelType? = ChannelType.NONE,
    var title: String? = "",
    var name: String? = "",

    @Column(name = "content", columnDefinition = "text")
    var text: String? = "",

    @Enumerated(EnumType.STRING)
    var contentsStyle: ContentsStyle? = ContentsStyle.REVIEW,

    var reward: BigDecimal? = BigDecimal.ZERO,

    var isPaidAds: Boolean? = false,
    var adsPrice: String? = "",
    var isBurdenFee: Boolean? = false,

    var recruitDeadLineDate: LocalDateTime? = null,
    var contentsCompletedDate: LocalDateTime? = null,

    @Enumerated(EnumType.STRING)
    var cashReceiptType: CashReceiptType? = CashReceiptType.UNISSUED,
    var cashReceiptIssuanceType: Char? = null,
    var cashReceiptsNumber: String = "",

    var isIssuedTaxInvoice: Boolean? = false,
    var taxInvoiceNumber: String = "",

    var isConfirmed: Boolean? = false,
    var isPaidReward: Boolean? = false,
    var isCompletedContents: Boolean? = false,

    var totalPaymentPrice: BigDecimal? = BigDecimal.ZERO,

    var orderNumber: String? = "",

    @OneToMany(mappedBy = "contest", cascade = [CascadeType.ALL])
    var contestAttachments: List<ContestAttachment>? = mutableListOf(),

    @OneToMany(mappedBy = "contest")
    var applies: List<Apply>? = mutableListOf(),

    @ManyToOne(fetch = FetchType.LAZY, cascade = [CascadeType.ALL])
    @JoinColumn(name = "MEMBER_ID")
    var member: Member? = null

) : Auditable<Long>()

enum class CashReceiptType(val type: String) {
    DEDUCTION("소득공제용"),
    EVIDENCE("지출증빙용"),
    UNISSUED("미발행")
}

enum class ContentsStyle(val style: String) {
    REVIEW("리뷰"),
    PPL("PPL")
}

enum class ChannelType(val type: String) {
    NONE("없음"),
    YOUTUBE("유튜브"),
    INSTAGRAM("인스타그램"),
    FACEBOOK("페이스북"),
    BLOG("블로그")
}

enum class ContestType(val type: String) {
    STANDARD("일반"),
    PREMIUM("프리미엄")
}