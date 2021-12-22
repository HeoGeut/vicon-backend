package com.vicon.viconbackend.features.admin.contest

import com.vicon.viconbackend.domain.apply.Apply
import com.vicon.viconbackend.domain.apply.ApplyAttachment
import com.vicon.viconbackend.domain.apply.ApplyLink

data class ApplyDetailDTO(
    val username: String,
    val channelInfo: String,
    val successStory: String,
    val storyboardText: String,
    val storyboardDraw: String,
    val expectEffect: String,
    val files: List<ApplyAttachment>,
    val links: List<ApplyLink>
) {
    companion object {
        fun of(apply: Apply): ApplyDetailDTO {
            return ApplyDetailDTO(
                username = apply.member!!.username,
                channelInfo = apply.channelInfo!!,
                successStory = apply.successStory!!,
                storyboardText = apply.storyboardText!!,
                storyboardDraw = apply.storyboardDraw!!,
                expectEffect = apply.expectEffect!!,
                files = apply.applyAttachments,
                links = apply.links
            )
        }
    }
}