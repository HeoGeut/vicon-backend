package com.vicon.viconbackend.features.auth

import com.vicon.viconbackend.domain.member.Member
import com.vicon.viconbackend.domain.member.MemberRepository
import org.springframework.stereotype.Service

@Service
class MemberService(
    val memberRepository: MemberRepository
) {
    fun save(member: Member) = memberRepository.save(member)
    fun findTop6By() = memberRepository.findTop6By()
}