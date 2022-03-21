package com.vicon.viconbackend.features.contest

import com.vicon.viconbackend.domain.contest.Contest
import com.vicon.viconbackend.domain.contest.ContestRepository
import org.springframework.data.domain.AbstractPageRequest
import org.springframework.stereotype.Service

@Service
class ContestService(
    val contestRepository: ContestRepository
) {
    fun findTop9ByOpenContest() = contestRepository.findTop9ByIsConfirmed(true)

    fun findTop3ByOrderByRecruitDeadLineDate() =
        contestRepository.findTop3ByOrderByRecruitDeadLineDate()

    fun save(contest: Contest) = contestRepository.save(contest)

    fun findById(id: Long) = contestRepository.findById(id)

    fun findTop10ByCloseContest() =
        contestRepository.findTop10ByIsCompletedContents(true)

    fun findAllByPageable(pageRequest: AbstractPageRequest) =
        contestRepository.findByEnabled(pageRequest)

    fun delete(id: String): Boolean {
        val contest = contestRepository.findById(id.toLong()).get()
        return try {
            contest.enabled = false
            this.save(contest)
            true
        } catch (e: Exception) {
            false
        }
    }

    fun saveAll(contests: List<Contest>): MutableList<Contest> = contestRepository.saveAll(contests)

    fun findByOrderNumber(orderId: String) = contestRepository.findByOrderNumber(orderId)
}