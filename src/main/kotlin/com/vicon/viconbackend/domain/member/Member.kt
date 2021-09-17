package com.vicon.viconbackend.domain.member

import com.vicon.viconbackend.domain.apply.Apply
import com.vicon.viconbackend.domain.common.Auditable
import com.vicon.viconbackend.domain.contest.Contest
import com.vicon.viconbackend.features.auth.MemberCreateForm
import javax.persistence.Entity
import javax.persistence.OneToMany

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

    @OneToMany(mappedBy = "member")
    var applies: List<Apply>? = mutableListOf(),

    @OneToMany(mappedBy = "member")
    var contests: List<Contest>? = mutableListOf()

) : Auditable<Long>() {
    fun from(memberCreateForm: MemberCreateForm): Member {
        this.memberId = memberCreateForm.memberId
        this.memberPw = memberCreateForm.memberPw
        this.phoneNumberFront = memberCreateForm.phoneNumberFront
        this.phoneNumberMiddle = memberCreateForm.phoneNumberMiddle
        this.phoneNumberBack = memberCreateForm.phoneNumberBack
        this.emailFront = memberCreateForm.emailFront
        this.emailBack = memberCreateForm.emailBack
        this.companyName = memberCreateForm.companyName
        this.businessCategory = memberCreateForm.businessCategory
        this.websiteurl = memberCreateForm.websiteUrl
        this.channelCategory = memberCreateForm.channelCategory
        this.channelUrl = memberCreateForm.channelUrl
        this.subscriberAmount = memberCreateForm.subscriberAmount
        this.businessType = BusinessType.valueOf(memberCreateForm.businessType)
        this.channelType = memberCreateForm.channelType.toString()

        return this
    }
}

enum class BusinessType(val type: String) {
    NONE("없음"),
    INDIVIDUAL("개인사업자"),
    CORPORATION("법인사업자"),
    SIMPLE_TAX("간이과세자"),
    PUBLIC_ENTERPRISE("공기업")
}
