package com.vicon.viconbackend.features.auth

import com.vicon.viconbackend.domain.member.Member
import com.vicon.viconbackend.domain.member.MemberRepository
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service

@Service
class MemberService(
    val authService: AuthService,
    val memberRepository: MemberRepository,
) {

    fun findTop9By() = memberRepository.findTop9ByOrderByCreatedAtDesc()
    fun findById(id: Long) = memberRepository.findById(id)
    fun findByUsername(username: String) = memberRepository.findByUsername(username)

    fun findTop10By() = memberRepository.findTop10By()
    fun findAllByPageable(pageRequest: Pageable): Page<Member> = memberRepository.findByEnabled(pageRequest)

    fun save(member: Member) {

//        val encodedPassword = passwordEncoder.encode(member.password)
//        member.password = encodedPassword

//        member.run {
//            this.memberPw = passwordEncoder.encode(this.memberPw)
//        }

        memberRepository.save(member)
    }

    fun saveAll(members: List<Member>): MutableList<Member> = memberRepository.saveAll(members)

    fun delete(memberId: String): Boolean {
        val member = memberRepository.findById(memberId.toLong()).get()
        return try {
            member.enabled = false
            this.save(member)
            true
        } catch (e: Exception) {
            false
        }
    }
}