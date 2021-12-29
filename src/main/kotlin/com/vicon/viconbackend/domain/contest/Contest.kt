package com.vicon.viconbackend.domain.contest

import com.vicon.viconbackend.domain.apply.Apply
import com.vicon.viconbackend.domain.common.Auditable
import com.vicon.viconbackend.domain.member.Member
import com.vicon.viconbackend.domain.payment.Payment
import com.vicon.viconbackend.features.contest.ContestCreateForm
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
    var adsPrice: BigDecimal? = BigDecimal.ZERO,
    var isBurdenFee: Boolean? = false,

    var recruitDeadLineDate: LocalDateTime? = null,
    var contentsCompletedDate: LocalDateTime? = null,

    var recruitNumber: BigDecimal = BigDecimal.ZERO,

    @Enumerated(EnumType.STRING)
    var cashReceiptType: CashReceiptType? = CashReceiptType.UNISSUED,
    var cashReceiptIssuanceType: String? = null,
    var cashReceiptsNumber: String = "",

    var isIssuedTaxInvoice: Boolean? = false,
    var taxInvoiceNumber: String = "",

    var isConfirmed: Boolean? = false,
    var isPaidReward: Boolean? = false,
    var isCompletedContents: Boolean? = false,

    var totalPaymentPrice: BigDecimal? = BigDecimal.ZERO,

    var orderNumber: String? = "",

    var enabled: Boolean? = false,

    @OneToMany(mappedBy = "contest", cascade = [CascadeType.ALL])
    var contestAttachments: List<ContestAttachment>? = mutableListOf(),

    @OneToMany(mappedBy = "contest")
    var applies: List<Apply>? = mutableListOf(),

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "MEMBER_ID")
    var member: Member? = null,

    @OneToOne(mappedBy = "contest")
    @JoinColumn(name = "PAYMENT_ID")
    var payment: Payment? = null

) : Auditable<Long>() {
    fun from(createForm: ContestCreateForm): Contest {
        this.type = createForm.c_type.run {
            when (this) {
                "1" -> ContestType.STANDARD
                else -> ContestType.PREMIUM
            }
        }
        this.category = createForm.businessCategory
        this.title = createForm.title
        this.name = createForm.name
        this.text = createForm.text
//      need add file
        this.recruitNumber = createForm.recruitNumber.toBigDecimal()
        this.reward = createForm.c_reward.replace(",", "").toBigDecimal()
        this.isPaidAds = createForm.c_ad_chk.run {
            when (this) {
                "1" -> true
                else -> false
            }
        }
        this.adsPrice = createForm.c_ad_price.replace(",", "").toBigDecimal()
        this.isBurdenFee = createForm.burdenFee.run {
            when (this) {
                "1" -> true
                else -> false
            }
        }
        val deadLineList = createForm.c_deadline.split("-").map { it.toInt() }
        this.recruitDeadLineDate = LocalDateTime.of(deadLineList[0], deadLineList[1], deadLineList[2], 0, 0)

        val dueDateList = createForm.c_duedate.split("-").map { it.toInt() }
        this.contentsCompletedDate = LocalDateTime.of(dueDateList[0], dueDateList[1], dueDateList[2], 0, 0)

        this.totalPaymentPrice = createForm.totalReward.toBigDecimal()

        this.enabled = createForm.enabled.run {
            when (this) {
                "1" -> true
                else -> false
            }
        }

        return this
    }
}

enum class CashReceiptType(val type: String) {
    UNISSUED("미발행"),
    DEDUCTION("소득공제용"),
    EVIDENCE("지출증빙용")
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