package com.vicon.viconbackend.features.admin.contest

import com.vicon.viconbackend.domain.contest.ChannelType
import com.vicon.viconbackend.domain.contest.Contest
import java.time.format.DateTimeFormatter


data class AdminContestDTO(
    val id: String,
    val contentsStyle: String,
    val category: String,
    val channelType: String,
    val title: String,
    val name: String,
    val reward: String,
    val recruitDeadLineDate: String,
    val applyNumber: String,
    val totalPayments: String,

    //작업여부
    val isCompletedContents: Boolean,
    //입금여부
    val paymentStatus: String,
    //검토여부
    val isConfirmed: Boolean,
    //지급여부
    val isPaidReward: Boolean,

    val createdDate: String
) {
    companion object {
        fun of(contest: Contest): AdminContestDTO {
            return AdminContestDTO(
                id = contest.id.toString(),
                contentsStyle = contest.contentsStyle!!.style,
                category = contest.category,
                channelType = "",
                title = contest.title!!,
                name = contest.name!!,
                reward = "${contest.reward!!.toInt()}원",
                recruitDeadLineDate = contest.recruitDeadLineDate!!.format(DateTimeFormatter.ofPattern("yy-MM-dd")),
                applyNumber = "${contest.applies!!.size}명",
                totalPayments = "${contest.totalPaymentPrice!!.toInt()}원",
                isCompletedContents = contest.isCompletedContents!!,
                paymentStatus = contest.payment?.status ?: "",
                isConfirmed = contest.isConfirmed!!,
                isPaidReward = contest.isPaidReward!!,
                createdDate = contest.createdAt!!.format(DateTimeFormatter.ofPattern("yy-MM-dd HH:mm:ss"))
            )
        }
    }
}