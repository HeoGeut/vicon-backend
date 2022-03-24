package com.vicon.viconbackend.features.reivew

import com.vicon.viconbackend.domain.contest.ContentsStyle
import com.vicon.viconbackend.domain.contest.Contest
import com.vicon.viconbackend.domain.member.Member
import com.vicon.viconbackend.domain.review.Review
import java.math.BigDecimal

class ReviewDto {

    data class ReviewResponseDto(
        val id : String = "",
        val thumbnailImage: String? = "",

        val isReview: Boolean = true,
        val contestTitle: String = "",
        val contestId: String = "",

        val reviewTitle: String = "",
        val reviewContent: String = "",

        val memberProfileImage: String? = "",
        val username: String = "",
        val star: BigDecimal = BigDecimal(5),

        val createdDate: String = "",
        val reward: String = "",
        val category: String = "",
        val applyNumber: String = ""
    ) {
        companion object {
            fun of(review: Review): ReviewResponseDto {
                return ReviewResponseDto(
                    id = review.id.toString(),
                    thumbnailImage = "",
                    isReview = review.contest!!.contentsStyle == ContentsStyle.REVIEW,
                    contestTitle = review.contest!!.title.toString(),
                    contestId = review.contest!!.id.toString(),
                    reviewTitle = review.title.toString(),
                    reviewContent = review.content.toString(),
                    memberProfileImage = review.member!!.profileImage,
                    username = review.member!!.username,
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

    data class ReviewRequestDto(
        val title: String? = "",
        val youtubeUrl: String? = "",
        val content: String? = "",
        val star: String? = "5",
        val contestId: String? = "",
    ) {
        fun toEntity(member: Member, contest: Contest): Review {
            return Review(
                title = this.title,
                youtubeUrl = this.youtubeUrl,
                content = this.content,
                star = this.star,
                contest = contest,
                member = member,
            )
        }
    }

    data class ReviewDetailsResponseDto(
        val title: String,
        val star: String,
        val username: String,
        val createdDate: String,
        val isReview: Boolean = true,

        val contestInReview: ContestInReviewResponseDto,
        val profileImage: String,
        val companyName: String,
        val content : String,
    ) {
        companion object {
            fun of(review: Review): ReviewDetailsResponseDto {
                return ReviewDetailsResponseDto(
                    title = review.title!!,
                    star = review.star!!,
                    username = review.member!!.username,
                    createdDate = review.createdAt.toString().dropLast(9),
                    isReview = review.contest!!.contentsStyle == ContentsStyle.REVIEW,
                    contestInReview = ContestInReviewResponseDto.of(review.contest!!),
                    profileImage = review.member!!.profileImage ?: "",
                    companyName = review.member!!.companyName!!,
                    content = review.content!!
                )
            }
        }
    }

    data class ContestInReviewResponseDto(
        val contestId : String,
        val contentsStyle: ContentsStyle?,
        val title: String,
        val createdDate: String,
        val reward: String,
        val category: String,
        val applyNumber: String,
    ) {
        companion object {
            fun of(contest: Contest): ContestInReviewResponseDto {
                return ContestInReviewResponseDto(
                    contestId = contest.id.toString(),
                    contentsStyle = contest.contentsStyle,
                    title = contest.title!!,
                    createdDate = contest.createdAt.toString().dropLast(9),
                    reward = contest.reward!!.toInt().div(10000).toString(),
                    category = contest.category,
                    applyNumber = ((contest.applies?.size) ?: 0).toString()
                )
            }
        }
    }
}