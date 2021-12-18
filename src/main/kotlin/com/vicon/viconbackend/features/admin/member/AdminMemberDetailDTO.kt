package com.vicon.viconbackend.features.admin.member

import com.vicon.viconbackend.domain.member.Member
import java.time.format.DateTimeFormatter

data class AdminMemberDetailDTO(
    val mailCertification: Boolean = false,
    val profileImage: String = "",
    val createdDate: String = "",
    val username: String = "",
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
    val openedContests: List<ContestInfo>? = mutableListOf(),
    val enteredContests: List<ContestInfo>? = mutableListOf()
) {
    companion object {
        fun of(member: Member): AdminMemberDetailDTO {
            return AdminMemberDetailDTO(
                mailCertification = member.isCertificated ?: false,
                profileImage = member.profileImage ?: "/image/noimage.png",
                createdDate = member.createdAt!!.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")),
                username = member.username,
                phoneNumberFront = member.phoneNumberFront!!,
                phoneNumberMiddle = member.phoneNumberMiddle!!,
                phoneNumberBack = member.phoneNumberBack!!,
                emailFront = member.emailFront!!,
                emailBack = member.emailBack!!,
                companyName = member.companyName ?: "",
                businessCategory = member.businessCategory ?: "",
                websiteUrl = member.websiteUrl ?: "",
                businessType = member.businessType?.ordinal ?: 0,
                businessNumber = member.businessNumber ?: "",
                openedContests = openedContests(member),
                enteredContests = enteredContests(member)
            )
        }

        private fun enteredContests(member: Member): List<ContestInfo>? {
            if (member.applies?.isEmpty() == true) {
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
            if (member.contests?.isEmpty() == true) {
                return null
            }

            val contests = member.contests!!
            return contests.map {
                ContestInfo(
                    id = it.id.toString(),
                    info = "· ${it.title} / ${it.category} / ${it.channelType?.type ?: ""} / " +
                            "${it.contentsStyle?.style ?: ""} / ${it.applies?.size ?: ""} " +
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
