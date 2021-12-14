package com.vicon.viconbackend.features.auth

import com.vicon.viconbackend.domain.member.Member
import com.vicon.viconbackend.domain.member.MemberRepository
import org.springframework.stereotype.Service

@Service
class MemberService(
    val authService: AuthService,
    val memberRepository: MemberRepository,
) {

    fun findTop6By() = memberRepository.findTop6By()
    fun findById(id: Long) = memberRepository.findById(id)
    fun findByMemberId(memberId: String) = memberRepository.findByUsername(memberId)
    fun findTop10By() = memberRepository.findTop10By()

    fun save(member: Member) {
//        val encodedPassword = passwordEncoder.encode(member.password)
//        member.password = encodedPassword

//        member.run {
//            this.memberPw = passwordEncoder.encode(this.memberPw)
//        }

        memberRepository.save(member)
    }
}