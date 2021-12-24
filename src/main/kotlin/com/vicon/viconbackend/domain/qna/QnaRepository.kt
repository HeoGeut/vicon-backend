package com.vicon.viconbackend.domain.qna

import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository

interface QnaRepository : JpaRepository<Qna, Long> {
    fun findTop5By(): List<Qna>
    fun findByEnabled(pageable: Pageable, enabled: Boolean = true) : Page<Qna>
}