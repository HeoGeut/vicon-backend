package com.vicon.viconbackend.features.auth

import com.vicon.viconbackend.domain.member.Member

data class MemberDTO(
    val id: Long = 0,
    val profileImage: String = "",
    val memberId: String = "",
    val tryApply: String = "",
    val chosenApply: String = ""
) {
    companion object {
        fun of(member: Member): MemberDTO {
            return MemberDTO(
                id = member.id!!,
                profileImage = member.profileImage!!,
                memberId = member.memberId!!,
                tryApply = member.applies!!.size.toString(),
                chosenApply = member.applies!!.count { it.isConfirm == true }.toString()
            )
        }
    }
}