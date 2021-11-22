package com.vicon.viconbackend.features.auth

import com.vicon.viconbackend.domain.member.Member
import com.vicon.viconbackend.domain.member.MemberRepository
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import kotlin.math.log

@Service
class MemberService(
    val memberRepository: MemberRepository,
    val passwordEncoder: PasswordEncoder
) : UserDetailsService {

    fun findTop6By() = memberRepository.findTop6By()
    fun findById(id: Long) = memberRepository.findById(id)
    fun findByMemberId(memberId: String) = memberRepository.findByMemberId(memberId)
    fun findTop10By() = memberRepository.findTop10By()
    fun login(loginForm: LoginDTO): Boolean {
        val encodedPassword = memberRepository.findByMemberId(loginForm.memberId).get().memberPw
        return passwordEncoder.matches(loginForm.memberPw, encodedPassword)
    }

    fun save(member: Member) {
        val encodedPassword = passwordEncoder.encode(member.memberPw)
        member.memberPw = encodedPassword

//        member.run {
//            this.memberPw = passwordEncoder.encode(this.memberPw)
//        }

        memberRepository.save(member)
    }

    override fun loadUserByUsername(username: String): UserDetails {
        return memberRepository.findByMemberId(username).get().getAuthorities()
            ?: throw UsernameNotFoundException("$username Cannot Found")
    }
}