package com.vicon.viconbackend.features.mypage

import com.vicon.viconbackend.domain.member.Member

data class MemberInfoDTO(
    val id: Long = 0,
    val username: String = "",
    val phoneNumber: String = "",
    val profileImage : String = "",
    val email: String = "",
    val apply: String = "0",
    val elect: String = "0",
    val totalPrice: String = "0",

    val companyName: String = "",
    val businessCategory: String = "",
    val websiteUrl: String = "",
    val businessType: String = "",
    val businessNumber: String = "",

    val channelUrl: String = "",
    val channelCategory: String = "",
    val subscriberAmount: String = "",
    val channelType: String = ""
) {
    companion object {
        fun of(member: Member): MemberInfoDTO {
            return MemberInfoDTO(
                id = member.id!!,
                username = member.username,
                profileImage = member.profileImage ?: "",
                phoneNumber = "${member.phoneNumberFront}-${member.phoneNumberMiddle}-${member.phoneNumberBack}",
                email = "${member.emailFront}@${member.emailBack}",
                apply = member.applies?.size.toString(),
                elect = getElect(member)?.size.toString(),
                totalPrice = 0.toString(),
                companyName = member.companyName ?: "",
                businessCategory = member.businessCategory ?: "",
                websiteUrl = member.websiteUrl ?: "",
                businessType = member.businessType?.type ?: Member.BusinessType.NONE.type,
                businessNumber = member.businessNumber ?: "",
                channelUrl = member.channelUrl ?: "",
                channelCategory = member.channelCategory ?: "",
                subscriberAmount = member.subscriberAmount ?: "",
                channelType = member.channelType
            )
        }

        private fun getElect(member: Member) =
            member.applies?.filter { it.isConfirm == true }
    }
}

