package com.vicon.viconbackend.domain.member

import org.springframework.data.jpa.repository.JpaRepository

interface MemberRepository : JpaRepository<Member, Long> {
    fun findTop6By(): List<Member>
}