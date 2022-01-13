package com.vicon.viconbackend.features.mypage

import com.vicon.viconbackend.domain.apply.Apply

data class ApplyDTO(
    val memberId: String = "",
    val channelInfo: String? = "",
    val successStory: String? = "",
    val storyboardText: String? = "",
    val storyboardDraw: String? = "",
    val expectEffect: String? = "",
) {
    companion object {
        fun from(apply: Apply): ApplyDTO {
            return ApplyDTO(
                memberId = apply.member!!.username,
                channelInfo = apply.channelInfo!!,
                successStory = apply.successStory!!,
                storyboardText = apply.storyboardText!!,
                storyboardDraw = apply.storyboardDraw!!,
                expectEffect = apply.expectEffect!!
            )
        }
    }
}