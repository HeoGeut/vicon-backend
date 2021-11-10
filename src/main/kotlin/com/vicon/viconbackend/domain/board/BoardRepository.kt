package com.vicon.viconbackend.domain.board

import org.springframework.data.jpa.repository.JpaRepository

interface BoardRepository : JpaRepository<Board, Long> {
    fun findTop3By(): List<Board>
}