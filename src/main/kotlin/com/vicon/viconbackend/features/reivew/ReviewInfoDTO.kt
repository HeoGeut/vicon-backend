package com.vicon.viconbackend.features.reivew

import com.vicon.viconbackend.domain.contest.ContentsStyle
import com.vicon.viconbackend.domain.review.Review
import java.math.BigDecimal

data class ReviewInfoDTO(
    val thumbnailImage: String? = "",

    val isReview: Boolean = true,
    val contestTitle: String = "",
    val contestId: String = "",

    val reviewTitle: String = "",
    val reviewContent: String = "",

    val memberProfileImage: String? = "",
    val memberId: String = "",
    val star: BigDecimal = BigDecimal(5),

    val createdDate: String = "",
    val reward: String = "",
    val category: String = "",
    val applyNumber: String = ""
) {
    companion object {
        fun of(review: Review): ReviewInfoDTO {
            return com.vicon.viconbackend.features.reivew.ReviewInfoDTO(
                thumbnailImage = "",
                isReview = review.contest!!.contentsStyle == ContentsStyle.REVIEW,
                contestTitle = review.contest!!.title.toString(),
                contestId = review.contest!!.id.toString(),
                reviewTitle = review.title.toString(),
                reviewContent = review.content.toString(),
                memberProfileImage = review.member!!.profileImage,
                memberId = review.member!!.username.toString(),
                star = review.star?.toBigDecimal() ?: BigDecimal(5),
                createdDate = review.createdAt!!.toLocalDate().toString(),
                reward = (review.contest!!.reward!!.toInt() / 10000).toString(),
                category = review.contest!!.category,
                applyNumber = review.contest!!.applies?.size.toString()
            )
        }
    }

    @JvmName("getStar1")
    fun getStar(): BigDecimal {
        return this.star
    }
}