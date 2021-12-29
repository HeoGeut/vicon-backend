package com.vicon.viconbackend.domain.member

import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface MemberRepository : JpaRepository<Member, Long> {
    fun findTop9By(): List<Member>
    fun findByUsername(username: String) : Optional<Member>
    fun findTop10By(): List<Member>
    fun findByEnabled(pageable: Pageable, enabled: Boolean = true) : Page<Member>
}