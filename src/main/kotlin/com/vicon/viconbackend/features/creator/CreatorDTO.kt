package com.vicon.viconbackend.features.creator

import com.vicon.viconbackend.domain.member.Member

data class CreatorDTO(
    val profileImage: String? = "",
    val memberId: String = "",

    val applyNumber: String = "",
    val confirmNumber: String = "",
    val totalReward: String = ""
) {
    companion object {
        fun of(member: Member): CreatorDTO {
            return CreatorDTO(
                profileImage = member.profileImage,
                memberId = member.memberId!!,
                applyNumber = member.applies?.size.toString(),
                confirmNumber = member.applies?.count { it.isConfirm == true }.toString(),
                totalReward = "0"
            )
        }
    }
}