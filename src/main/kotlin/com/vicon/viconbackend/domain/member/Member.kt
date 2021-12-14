package com.vicon.viconbackend.domain.member

import com.fasterxml.jackson.annotation.JsonIgnore
import com.vicon.viconbackend.domain.apply.Apply
import com.vicon.viconbackend.domain.auth.AuthenticationPassword
import com.vicon.viconbackend.domain.common.Persistable
import com.vicon.viconbackend.domain.contest.Contest
import com.vicon.viconbackend.features.auth.MemberCreateForm
import org.hibernate.annotations.CreationTimestamp
import org.hibernate.annotations.UpdateTimestamp
import java.time.LocalDateTime
import javax.persistence.*
import javax.validation.constraints.NotEmpty

@Entity
class Member(
    @NotEmpty @Column(unique = true)
    var username: String = "",

    @JsonIgnore
    var password: String = "",

    var profileImage: String? = "",

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

    var enabled: Boolean = true,

    @OneToMany(mappedBy = "member")
    var applies: List<Apply>? = mutableListOf(),

    @OneToMany(mappedBy = "member")
    var contests: List<Contest>? = mutableListOf(),

    @CreationTimestamp
    var createdAt: LocalDateTime? = null,

    @UpdateTimestamp
    var modifiedAt: LocalDateTime? = null

) : Persistable<Long>() {

    fun from(memberCreateForm: MemberCreateForm): Member {
        this.username = memberCreateForm.mem_id
        this.password = memberCreateForm.mem_pw
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

        return this
    }

//    fun getPassword(): String {
//        return password
//    }
//
//    fun setPassword(password: String) {
//        this.password = password
//    }

//    fun getUsername(): String {
//        return username
//    }

    fun isAccountNonExpired(): Boolean {
        return enabled
    }

    fun isAccountNonLocked(): Boolean {
        return enabled
    }

    fun isCredentialsNonExpired(): Boolean {
        return enabled
    }

    fun isEnabled(): Boolean {
        return enabled
    }

    fun validEnabled() {
        if (this.enabled.not()) throw IllegalAccessException("Should be enabled")
    }
//
//    fun updatePassword(auth: AuthenticationPassword, passwordEncoder: PasswordEncoder) {
//        if (this.password != passwordEncoder.encode(auth.password)) {
//            throw IllegalAccessException("User password not equals")
//        }
//
//        this.password = passwordEncoder.encode(auth.changePassword)
//    }
//
//    fun updatePassword(new: String, passwordEncoder: PasswordEncoder) {
//        this.password = passwordEncoder.encode(new)
//    }

    fun leave() {
        this.enabled = false
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
}
