package com.vicon.viconbackend.domain.payment

import com.vicon.viconbackend.domain.common.Persistable
import javax.persistence.Entity

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
    var approvedTime: Int? = 0

) : Persistable<Long>()