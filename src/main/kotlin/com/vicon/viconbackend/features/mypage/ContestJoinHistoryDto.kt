package com.vicon.viconbackend.features.mypage

import com.vicon.viconbackend.domain.contest.Contest
import java.time.LocalDateTime

data class ContestJoinHistoryDto (
    val id: String = "",
    val contestStyle: String = "",
    val title: String = "",
    val reward: String = "",
    val progress: String = "",
    val applyNumber: String = "",
    val applies: List<Applies> = listOf()
){
    companion object {
        fun of(contest: Contest): ContestHistoryDTO {
            return ContestHistoryDTO(
                id = contest.id.toString(),
                contestStyle = contest.contentsStyle!!.style,
                title = contest.title!!,
                isConfirmed = contest.isConfirmed!!,
                isCompleted = contest.isCompletedContents!!,
                reward = "${contest.reward!!.toInt().div(10000)}만원",
                progress = if (contest.contentsCompletedDate!!.isAfter(LocalDateTime.now())) "종료" else "진행",
                applyNumber = "${contest.applies?.size ?: 0}명",
                applies = contest.applies?.map { Applies.from(it) } ?: listOf()
            )
        }
    }
}