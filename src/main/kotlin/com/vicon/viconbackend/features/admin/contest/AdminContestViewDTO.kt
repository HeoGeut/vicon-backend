package com.vicon.viconbackend.features.admin.contest

import com.vicon.viconbackend.domain.contest.Contest
import com.vicon.viconbackend.domain.contest.ContestAttachment
import java.time.format.DateTimeFormatter

data class AdminContestViewDTO(
    val createdDate: String,
    val memberId: String,
    val username: String,
    val contestInfo: String,
    val title: String,
    val text: String,
    val contestAttachments: List<ContestAttachment>,

    val enteredContests: List<EnteredContest>,

    val applyNumber: String,
    val reward: String,
    val isPaidAds: Boolean,
    val adsPrice: String,
    val isBurdenFee: Boolean,
    val recruitDeadLineDate: String,
    val contentsCompletedDate: String,
    val payment: PaymentDetail
) {
    companion object {
        fun of(contest: Contest): AdminContestViewDTO {
            return AdminContestViewDTO(
                createdDate = contest.createdAt!!
                    .format(DateTimeFormatter.ofPattern("yy-MM-dd HH:mm:ss")),
                memberId = contest.member!!.id.toString(),
                username = contest.member!!.username,
                contestInfo = "${contest.category} / ${contest.channelType!!.type} / " +
                        "${contest.contentsStyle!!.name} / ${contest.applies!!.size}명",
                title = contest.title!!,
                text = contest.text!!,
                contestAttachments = contest.contestAttachments!!,
                enteredContests = enteredContest(contest),
                applyNumber = "${contest.applies?.size ?: 0} 명",
                reward = "${contest.reward!!.toInt()} 원",
                isPaidAds = contest.isPaidAds!!,
                adsPrice = "${contest.adsPrice!!.toInt()}원",
                isBurdenFee = contest.isBurdenFee!!,
                recruitDeadLineDate = contest.recruitDeadLineDate!!.toLocalDate().toString(),
                contentsCompletedDate = contest.contentsCompletedDate!!.toLocalDate().toString(),
                payment = paymentDetail(contest)
            )
        }

        private fun paymentDetail(contest: Contest): PaymentDetail {
            return PaymentDetail(
                cashReceipt = cashReceipt(contest),
                taxInvoice = taxInvoice(contest)
            )
        }

        private fun cashReceipt(contest: Contest): String {
            return when (contest.cashReceiptType!!.type) {
                "미발행" -> "미발행"
                "소득공제용" -> "소득공제용 ( 휴대폰번호 : ${contest.cashReceiptsNumber})"
                "지출증빙용" -> "지출증빙용 (${contest.cashReceiptsNumber})"
                else -> ""
            }
        }

        private fun taxInvoice(contest: Contest): String {
            return if (contest.isIssuedTaxInvoice!!) {
                "발행 (${contest.taxInvoiceNumber})"
            } else {
                "미발행"
            }
        }

        private fun enteredContest(contest: Contest): List<EnteredContest> {
            return contest.applies!!.map {
                EnteredContest(
                    username = it.member!!.username,
                    isConfirmedApply = it.isConfirm!!,
                    applyId = it.id.toString(),
//                    apply = applyDetailDto(it),
                    createdDate = it.createdAt!!.format(DateTimeFormatter.ofPattern("yy-MM-dd HH:mm:ss"))
                )
            }
        }

//        private fun applyDetailDto(apply: Apply): ApplyDetailDTO {
//            return ApplyDetailDTO(
//                username = apply.member!!.username,
//                channelInfo = apply.channelInfo!!,
//                successStory = apply.successStory!!,
//                storyboardText = apply.storyboardText!!,
//                storyboardDraw = apply.storyboardDraw!!,
//                expectEffect = apply.expectEffect!!,
//                files = apply.applyAttachments,
//                links = apply.links
//            )
//        }
    }
}

data class PaymentDetail(
    val cashReceipt: String,
    val taxInvoice: String
)

data class EnteredContest(
    val username: String,
    val isConfirmedApply: Boolean,
    val applyId: String,
//    val apply: ApplyDetailDTO,
    val createdDate: String
)
