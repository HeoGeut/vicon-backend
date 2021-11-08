package com.vicon.viconbackend.features.reivew

import com.vicon.viconbackend.domain.contest.ContentsStyle
import com.vicon.viconbackend.domain.review.Review

data class ReviewInfoDTO(
    val thumbnailImage: String? = "",

    val isReview: Boolean = true,
    val contestTitle: String = "",
    val contestId: String = "",

    val reviewTitle: String = "",
    val reviewContent: String = "",

    val memberProfileImage: String? = "",
    val memberId: String = "",
    val star: Int = 5,

    val createdDate: String = "",
    val reward: String = "",
    val category: String = "",
    val applyNumber: String = ""
) {
    companion object {
        fun of(review: Review): ReviewInfoDTO {
            return ReviewInfoDTO(
                thumbnailImage = "",
                isReview = review.contest!!.contentsStyle == ContentsStyle.REVIEW,
                contestTitle = review.contest!!.title.toString(),
                contestId = review.contest!!.id.toString(),
                reviewTitle = review.title.toString(),
                reviewContent = review.content.toString(),
                memberProfileImage = review.member!!.profileImage,
                memberId = review.member!!.memberId.toString(),
                star = review.star?.toInt() ?: 5,
                createdDate = review.createdAt!!.toLocalDate().toString(),
                reward = review.contest!!.reward.toString(),
                category = review.contest!!.category,
                applyNumber = review.contest!!.applies?.size.toString()
            )
        }
    }
}