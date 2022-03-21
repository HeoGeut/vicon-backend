package com.vicon.viconbackend.domain.payment

import com.fasterxml.jackson.databind.JsonNode
import com.vicon.viconbackend.domain.common.Persistable
import com.vicon.viconbackend.domain.contest.Contest
import com.vicon.viconbackend.domain.contest.ContestRepository
import javax.persistence.Entity
import javax.persistence.OneToOne

@Entity
data class Payment(
    var mId: String? = "",
    var paymentKey: String? = "",
    var orderId: String? = "",
    var currency: String? = "",
    var method: String? = "",
    var totalAmount: String? = "",
    var balanceAmount: String? = "",
    var status: String? = "",
    var requestedAt: String? = "",
    var approvedAt: String? = "",
    var useEscrow: String? = "",
    var card: String? = "",
    var accountNumber: String? = "",
    var accountType: String? = "",
    var bank: String? = "",
    var customerName: String? = "",
    var dueDate: String? = "",
    var expired: String? = "",
    var settlementStatus: String? = "",
    var refundStatus: String? = "",
    var mobilePhone: String? = "",
    var giftCertificate: String? = "",
    var cashReceiptType: String? = "",
    var cashReceiptAmount: String? = "",
    var cashReceiptTaxFreeAmount: String? = "",
    var cashReceiptIssueNumber: String? = "",
    var cashReceiptReceiptUrl: String? = "",
    var discount: String? = "",
    var cancels: String? = "",
    var secret: String? = "",
    var useDiscount: String? = "",
    var discountAmount: String? = "",
    var useCashReceipt: String? = "",
    var taxType: String? = "",
    var taxNumber: String? = "",
    var approvedTime: Int? = 0,

    @OneToOne
    var contest: Contest? = null

) : Persistable<Long>(){
    fun from(res: JsonNode, contest: Contest) : Payment {
        return Payment(
            mId = res.get("mId").asText(),
            paymentKey = res.get("paymentKey").asText(),
            orderId = res.get("orderId").asText(),
            currency = res.get("currency").asText(),
            method = res.get("method").asText(),
            totalAmount = res.get("totalAmount").asText(),
            balanceAmount = res.get("balanceAmount").asText(),
            status = res.get("status").asText(),
            requestedAt = res.get("requestedAt").asText(),
            approvedAt = res.get("approvedAt").asText(),
            useEscrow = res.get("useEscrow").asText(),
            card = res.get("card").asText(),
            accountNumber = res.get("virtualAccount").get("accountNumber").asText(),
            accountType = res.get("virtualAccount").get("accountType").asText(),
            bank = res.get("virtualAccount").get("bank").asText(),
            customerName = res.get("virtualAccount").get("customerName").asText(),
            dueDate = res.get("virtualAccount").get("dueDate").asText(),
            expired = res.get("virtualAccount").get("expired").asText(),
            settlementStatus = res.get("virtualAccount").get("settlementStatus").asText(),
            refundStatus = res.get("virtualAccount").get("refundStatus").asText(),
            mobilePhone = res.get("mobilePhone").asText(),
            giftCertificate = res.get("giftCertificate").asText(),
            cashReceiptType = res.get("cashReceipt").asText(),
            discount = res.get("discount").asText() ?: "",
            cancels = res.get("cancels").asText() ?: "",
            secret = res.get("secret").asText() ?: "",
            useDiscount = res.get("useDiscount").asText() ?: "",
            discountAmount = res.get("discountAmount").asText() ?: "",
            useCashReceipt = res.get("useCashReceipt").asText() ?: "",
            contest = contest,
        )
    }
}