package com.vicon.viconbackend.features.auth

import com.vicon.viconbackend.domain.member.Member

data class MemberDetailsDto(
    val id: Long,
    val profileImage: String = "",
    val username: String = "",
    val password: String = "",
    val password2: String = "",
    val phoneNumberFront: String = "",
    val phoneNumberMiddle: String = "",
    val phoneNumberBack: String = "",
    val emailFront: String = "",
    val emailBack: String = "",
    val companyName: String = "",
    val businessCategory: String = "",
    val websiteUrl: String = "",
    val businessType: Int = 0,
    val businessNumber: String = "",
    val channelUrl: String = "",
    val channelCategory: String = "",
    val subscriberAmount: String = "",
    val channelType: String = "",
) {
    companion object {
        fun of(member: Member): MemberDetailsDto {
            return MemberDetailsDto(
                id = member.id ?: -1L,
                profileImage = member.profileImage ?: "",
                username = member.username,
                phoneNumberFront = member.phoneNumberFront ?: "",
                phoneNumberMiddle = member.phoneNumberMiddle ?: "",
                phoneNumberBack = member.phoneNumberBack ?: "",
                emailFront = member.emailFront ?: "",
                emailBack = member.emailBack ?: "",
                companyName = member.companyName ?: "",
                businessCategory = member.businessCategory ?: "",
                websiteUrl = member.websiteUrl ?: "",
                businessType = member.businessType?.ordinal ?: 0,
                businessNumber = member.businessNumber ?: "",
                channelUrl = member.channelUrl ?: "",
                channelCategory = member.channelCategory ?: "",
                subscriberAmount = member.subscriberAmount ?: "",
                channelType = member.channelType
            )
        }
    }

    fun toEntity(): Member {
        return Member(
            username = username,
            password = password,
            phoneNumberFront = phoneNumberFront,
            phoneNumberMiddle = phoneNumberMiddle,
            phoneNumberBack = phoneNumberBack,
            emailFront = emailFront,
            emailBack = emailBack,
            companyName = companyName,
            businessCategory = businessCategory,
            websiteUrl = websiteUrl,
            channelCategory = channelCategory,
            channelUrl = channelUrl,
            subscriberAmount = subscriberAmount,
            businessNumber = businessNumber,
        )
    }
}