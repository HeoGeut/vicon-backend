package com.vicon.viconbackend.domain.contest

import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository

interface ContestRepository : JpaRepository<Contest, Long> {
    fun findTop9ByIsConfirmedOrderByCreatedAtDesc(isConfirmed: Boolean): List<Contest>

    fun findTop3ByOrderByRecruitDeadLineDate(): List<Contest>

    fun findTop10ByIsCompletedContents(isCompleted: Boolean) : List<Contest>

    fun findByEnabled(pageable: Pageable, enabled : Boolean = true) : Page<Contest>

    fun findByOrderNumber(orderNumber: String) : Contest
}