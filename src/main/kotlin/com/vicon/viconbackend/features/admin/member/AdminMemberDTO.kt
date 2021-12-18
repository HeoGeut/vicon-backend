package com.vicon.viconbackend.features.admin.member

import com.vicon.viconbackend.domain.member.Member
import java.time.format.DateTimeFormatter

data class AdminMemberDTO(
    val id: String = "",
    val username: String = "",
    val phoneNumber: String = "",
    val email: String = "",
    val companyName: String = "",
    val businessCategory: String = "",
    val subscriberAmount: String = "",
    val channelType: String = "",
    val isCertificated: Boolean = false,
    val createdDate: String = ""
) {
    companion object {
        fun of(member: Member): AdminMemberDTO {
            return AdminMemberDTO(
                id = member.id.toString(),
                username = member.username,
                phoneNumber = "${member.phoneNumberFront}-${member.phoneNumberMiddle}-${member.phoneNumberBack}",
                email = "${member.emailFront}@${member.emailBack}",
                companyName = member.companyName ?: "",
                businessCategory = member.businessCategory ?: "",
                subscriberAmount = member.subscriberAmount ?: "",
                channelType = member.channelType,
                isCertificated = member.isCertificated ?: false,
                createdDate = member.createdAt!!.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"))
            )
        }
    }
}