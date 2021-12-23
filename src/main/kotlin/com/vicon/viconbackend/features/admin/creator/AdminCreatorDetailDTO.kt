package com.vicon.viconbackend.features.admin.creator

import com.vicon.viconbackend.domain.member.Member
import com.vicon.viconbackend.features.admin.member.ContestInfo
import java.time.format.DateTimeFormatter

data class AdminCreatorDetailDTO(
    val id: String = "",
    val profileImage: String = "",
    val createdDate: String = "",
    val username: String = "",
    val phoneNumber: String = "",
    val email: String = "",

    val companyName: String = "",
    val businessCategory: String = "",
    val websiteUrl: String = "",
    val businessType: String = "",
    val businessNumber: String = "",

    val channelUrl: String = "",
    val channelCategory: String = "",
    val subscriberAmount: String = "",
    val channelType: String = "",

    val openedContests: List<ContestInfo>? = mutableListOf(),
    val enteredContests: List<ContestInfo>? = mutableListOf()
) {
    companion object {
        fun of(member: Member): AdminCreatorDetailDTO {
            return AdminCreatorDetailDTO(
                id = member.id.toString(),
                profileImage = member.profileImage ?: "/image/noimage.png",
                createdDate = member.createdAt!!.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")),
                username = member.username,
                phoneNumber = "${member.phoneNumberFront!!}-${member.phoneNumberMiddle}-${member.phoneNumberBack}",
                email = "${member.emailFront}@${member.emailBack}",
                companyName = member.companyName ?: "",
                businessCategory = member.businessCategory ?: "",
                websiteUrl = member.websiteUrl ?: "",
                businessType = member.businessType!!.type,
                businessNumber = member.businessNumber ?: "",
                channelUrl = member.channelUrl!!,
                channelCategory = member.channelCategory!!,
                subscriberAmount = member.subscriberAmount!!,
                openedContests = openedContests(member),
                enteredContests = enteredContests(member)
            )
        }

        private fun enteredContests(member: Member): List<ContestInfo>? {
            if (member.applies.isNullOrEmpty()) {
                return null
            }

            val applies = member.applies!!

            return applies.map {
                ContestInfo(
                    id = it.contest!!.id.toString(),
                    info = "· ${it.contest!!.title ?: ""} " +
                            "(${it.createdAt!!.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"))})"
                )
            }
        }

        private fun openedContests(member: Member): List<ContestInfo>? {
            if (member.contests.isNullOrEmpty()) {
                return null
            }

            val contests = member.contests!!
            return contests.map {
                ContestInfo(
                    id = it.id.toString(),
                    info = "· ${it.title} / ${it.category} / ${it.channelType?.type ?: ""} / " +
                            "${it.contentsStyle?.style ?: ""} / " +
                            "(${it.createdAt!!.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"))})"
                )
            }
        }
    }
}

data class ContestInfo(
    val id: String = "",
    val info: String = ""
)