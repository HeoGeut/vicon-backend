package com.vicon.viconbackend.features.auth

import com.vicon.viconbackend.domain.member.Member
import com.vicon.viconbackend.domain.member.MemberRepository
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service
import java.util.*

@Service
class MemberService(
    val authService: AuthService,
    val memberRepository: MemberRepository,
) {

    fun findTop6By() = memberRepository.findTop6By()
    fun findById(id: Long) = memberRepository.findById(id)
    fun findByUsername(username: String) = memberRepository.findByUsername(username)

    fun findTop10By() = memberRepository.findTop10By()
    fun findAllByPageable(pageRequest: Pageable): Page<Member> = memberRepository.findBy(pageRequest)

    fun save(member: Member) {

//        val encodedPassword = passwordEncoder.encode(member.password)
//        member.password = encodedPassword

//        member.run {
//            this.memberPw = passwordEncoder.encode(this.memberPw)
//        }

        memberRepository.save(member)
    }

    fun saveAll(members: List<Member>) {
        memberRepository.saveAll(members)
    }
}