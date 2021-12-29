package com.vicon.viconbackend.features.contest

import com.vicon.viconbackend.domain.contest.ContentsStyle
import com.vicon.viconbackend.domain.contest.Contest
import com.vicon.viconbackend.domain.contest.ContestType
import java.time.LocalDate
import java.time.temporal.ChronoUnit

data class ContestDTO(
    val id: String = "",
    val memberProfileImage: String? = "",
    val name: String = "",
    val title: String = "",
    val text: String = "",

    val isReview: Boolean = true,
    val isStandard: Boolean = true,

    val restDate: String = "",
    val applyNumber: String = "",
    val recruitNumber: String = "",
    val totalReward: String = "",
    val individualReward: String = ""
) {
    companion object {
        fun of(contest: Contest): ContestDTO {
            return ContestDTO(
                id = contest.id.toString(),
                memberProfileImage = contest.member!!.profileImage ?: "",
                name = contest.name!!,
                title = contest.title!!,
                text = contest.text!!,
                isReview = contest.contentsStyle!! == ContentsStyle.PPL,
                isStandard = contest.type!! == ContestType.STANDARD,
                restDate = ChronoUnit.DAYS.between(
                    LocalDate.now(), contest.recruitDeadLineDate!!.toLocalDate()
                )
                    .toString(),
                applyNumber = contest.applies!!.size.toString(),
                recruitNumber = contest.recruitNumber.toInt().toString(),
                totalReward = "${contest.reward!!.toInt().div(10000)}만원",
                individualReward = "인당 ${(contest.reward!! / contest.recruitNumber).toInt().div(10000)}만원"
            )
        }
    }
}