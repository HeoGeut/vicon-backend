package com.vicon.viconbackend.domain.member

import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface MemberRepository : JpaRepository<Member, Long> {
    fun findTop6By(): List<Member>
    fun findByUsername(memberId: String) : Optional<Member>
    fun findTop10By(): List<Member>
    fun findBy(pageable: Pageable) : Page<Member>
}