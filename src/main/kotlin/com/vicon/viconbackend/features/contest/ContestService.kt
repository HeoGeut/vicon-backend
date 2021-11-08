package com.vicon.viconbackend.features.contest

import com.vicon.viconbackend.domain.contest.Contest
import com.vicon.viconbackend.domain.contest.ContestRepository
import org.springframework.stereotype.Service
import java.util.*

@Service
class ContestService(
    val contestRepository: ContestRepository
) {
    fun findTop6ByOpenContest(): List<Contest> {
        return contestRepository.findTop6ByIsConfirmed(true)
    }

    fun findTop3ByOrderByRecruitDeadLineDate(): List<Contest> {
        return contestRepository.findTop3ByOrderByRecruitDeadLineDate()
    }

    fun save(contest: Contest): Contest {
        return contestRepository.save(contest)
    }

    fun findById(id: Long): Optional<Contest> {
        return contestRepository.findById(id)
    }

    fun findTop10ByCloseContest() : List<Contest>{
        return contestRepository.findTop10ByIsCompletedContents(true)
    }
}