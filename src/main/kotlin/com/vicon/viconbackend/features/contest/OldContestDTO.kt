package com.vicon.viconbackend.features.contest

import com.fasterxml.jackson.annotation.JsonFormat
import com.vicon.viconbackend.domain.contest.ContentsStyle
import com.vicon.viconbackend.domain.contest.Contest
import com.vicon.viconbackend.domain.contest.ContestType
import org.springframework.format.annotation.DateTimeFormat
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.temporal.ChronoUnit

data class OldContestDTO(
    val contestId: String = "",
    val isStandard: Boolean = true,

    val memberProfileImage: String? = "",
    val name: String = "",
    val applyNumber: String = "",
    val recruitDeadLineDate: String = "",
    val restDate: String = "",

    val isReview: Boolean = true,
    val createdDate: String = "",
    val title: String = "",
    val totalReward: String = "",
    val category: String = "",
    val text: String = ""
) {
    companion object {
        fun of(contest: Contest): OldContestDTO {
            return OldContestDTO(
                contestId = contest.id.toString(),

                isStandard = contest.type!! == ContestType.STANDARD,

                memberProfileImage = contest.member!!.profileImage ?: "",
                name = contest.name!!,
                applyNumber = contest.applies!!.size.toString(),
                recruitDeadLineDate = contest.recruitDeadLineDate!!.toString().dropLast(9),
                restDate = getRestDate(contest),

                isReview = contest.contentsStyle!! == ContentsStyle.PPL,
                createdDate = contest.createdAt.toString().dropLast(9),
                title = contest.title!!,
                totalReward = "${contest.reward!!.toInt().div(10000)}만원",
                category = contest.category,
                text = contest.text!!
            )
        }

        private fun getRestDate(contest: Contest): String {
            val date = ChronoUnit.DAYS.between(
                LocalDate.now(), contest.recruitDeadLineDate!!.toLocalDate()
            )
            return if (date < 0) {
                "마감되었습니다"
            } else {
                date.toString() + "일 전"
            }
        }
    }
}