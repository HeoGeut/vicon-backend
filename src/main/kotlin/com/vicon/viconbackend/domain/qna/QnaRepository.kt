package com.vicon.viconbackend.domain.qna

import org.springframework.data.jpa.repository.JpaRepository

interface QnaRepository : JpaRepository<Qna, Long> {
    fun findTop5By(): List<Qna>
}