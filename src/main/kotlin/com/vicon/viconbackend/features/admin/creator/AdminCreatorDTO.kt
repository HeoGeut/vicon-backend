package com.vicon.viconbackend.features.admin.creator

import com.vicon.viconbackend.domain.member.Member
import java.time.format.DateTimeFormatter

data class AdminCreatorDTO(
    val memberId: String = "",
    val username: String = "",
    val phoneNumber: String = "",
    val email: String = "",
    val companyName: String = "",
    val businessCategory: String = "",
    val subscriberAmount: String = "",
    val channelUrl: String = "",
    val channelType: String = "",
    val createdDate: String = ""
) {
    companion object {
        fun of(member: Member): AdminCreatorDTO {
            return AdminCreatorDTO(
                memberId = member.id.toString(),
                username = member.username,
                phoneNumber = "${member.phoneNumberFront}-${member.phoneNumberMiddle}-${member.phoneNumberBack}",
                email = "${member.emailFront}@${member.emailBack}",
                companyName = member.companyName!!,
                businessCategory = member.businessCategory!!,
                subscriberAmount = member.subscriberAmount!!,
                channelUrl = member.channelUrl!!,
                channelType = member.channelType,
                createdDate = member.createdAt!!.format(DateTimeFormatter.ofPattern("yy-MM-dd HH:mm:ss"))
            )
        }
    }
}