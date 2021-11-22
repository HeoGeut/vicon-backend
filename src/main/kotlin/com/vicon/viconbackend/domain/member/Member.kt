package com.vicon.viconbackend.domain.member

import com.vicon.viconbackend.domain.apply.Apply
import com.vicon.viconbackend.domain.common.Auditable
import com.vicon.viconbackend.domain.contest.Contest
import com.vicon.viconbackend.features.auth.MemberCreateForm
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.User
import java.util.stream.Collectors
import javax.persistence.*

@Entity
data class Member(
    var profileImage: String? = "",
    var memberId: String? = "",
    var memberPw: String? = "",

    var phoneNumberFront: String? = "",
    var phoneNumberMiddle: String? = "",
    var phoneNumberBack: String? = "",

    var emailFront: String? = "",
    var emailBack: String? = "",

    var companyName: String? = "",
    var businessCategory: String? = "",
    var websiteurl: String? = "",

    var channelCategory: String? = "",
    var channelUrl: String? = "",
    var subscriberAmount: String? = "",

    var businessType: BusinessType? = BusinessType.NONE,
    var channelType: String = "",
    var businessNumber: String? = "",

    var isCertificated: Boolean? = false,

    @Enumerated(EnumType.STRING)
    @ElementCollection(fetch = FetchType.EAGER)
    var roles: MutableSet<MemberRole>? = mutableSetOf(MemberRole.USER),

    @OneToMany(mappedBy = "member")
    var applies: List<Apply>? = mutableListOf(),

    @OneToMany(mappedBy = "member")
    var contests: List<Contest>? = mutableListOf()

) : Auditable<Long>() {
    fun from(memberCreateForm: MemberCreateForm): Member {
        this.memberId = memberCreateForm.mem_id
        this.memberPw = memberCreateForm.mem_pw
        this.phoneNumberFront = memberCreateForm.mem_hp1
        this.phoneNumberMiddle = memberCreateForm.mem_hp2
        this.phoneNumberBack = memberCreateForm.mem_hp3
        this.emailFront = memberCreateForm.mem_email1
        this.emailBack = memberCreateForm.mem_email2
        this.companyName = memberCreateForm.mem_company
        this.businessCategory = memberCreateForm.mem_service
        this.websiteurl = memberCreateForm.mem_website
        this.channelCategory = memberCreateForm.mem_ch_category
        this.channelUrl = memberCreateForm.mem_ch_url
        this.subscriberAmount = memberCreateForm.mem_ch_subscriber
        this.businessType = BusinessType.valueOf(memberCreateForm.mem_business_type!!)
        this.channelType = memberCreateForm.channelType.toString()
        this.roles = mutableSetOf(MemberRole.USER)

        return this
    }

    fun getAuthorities(): User {
        return User(
            this.memberId, this.memberPw,
            this.roles!!.stream().map { role -> SimpleGrantedAuthority("ROLE_$role") }.collect(Collectors.toSet())
        )
    }
}

enum class BusinessType(val type: String) {
    NONE("없음"),
    INDIVIDUAL("개인사업자"),
    CORPORATION("법인사업자"),
    SIMPLE_TAX("간이과세자"),
    PUBLIC_ENTERPRISE("공기업")
}

enum class MemberRole {
    ADMIN, USER
}