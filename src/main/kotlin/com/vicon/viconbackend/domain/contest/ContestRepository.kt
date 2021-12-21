package com.vicon.viconbackend.domain.contest

import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository

interface ContestRepository : JpaRepository<Contest, Long> {
    fun findTop6ByIsConfirmed(isConfirmed: Boolean): List<Contest>

    fun findTop3ByOrderByRecruitDeadLineDate(): List<Contest>

    fun findTop10ByIsCompletedContents(isCompleted: Boolean) : List<Contest>

    fun findBy(pageable: Pageable) : Page<Contest>
}