package com.vicon.viconbackend.features.auth

import com.vicon.viconbackend.domain.member.Member
import com.vicon.viconbackend.domain.member.MemberRepository
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service

@Service
class AuthService(
    private val memberRepository: MemberRepository,
) {
    private val LOGGER = LoggerFactory.getLogger(AuthService::class.java)

    fun login(loginForm: LoginDTO): Member? {

        val findMember = memberRepository.findByUsername(loginForm.memberId)
        if (!findMember.isPresent) {
            return null
        }
        return if (loginForm.memberPw == findMember.get().password) {
            findMember.get()
        } else {
            null
        }
    }

}