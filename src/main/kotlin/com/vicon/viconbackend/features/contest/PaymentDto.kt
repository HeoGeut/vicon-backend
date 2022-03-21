package com.vicon.viconbackend.features.contest

import com.vicon.viconbackend.domain.payment.Payment

class PaymentDto {
    data class PaymentSuccessResponseDto(
        val mId: String = "",
        val bank: String = "",
        val customerName: String = "",
        val accountNumber: String = "",
        val totalAmount: String = "",
        val businessNumber: String = "",
    ){
        companion object {
            fun of(payment: Payment) : PaymentSuccessResponseDto{
                return PaymentSuccessResponseDto(
                    mId = payment.mId!!,
                    bank = payment.bank!!,
                    customerName = payment.customerName!!,
                    accountNumber = payment.accountNumber!!,
                    totalAmount = payment.totalAmount!!,
                    businessNumber = payment.contest!!.member!!.businessNumber ?: ""
                )
            }
        }
    }
}