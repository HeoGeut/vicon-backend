package com.vicon.viconbackend.features.reivew

import com.vicon.viconbackend.domain.review.Review
import java.math.BigDecimal

data class ReviewMainDto(
    val youtubeUrl: String? = "",
    val title: String = "",
    val star: BigDecimal = BigDecimal.ZERO,
    val createDate: String = "",
    val content: String = ""
) {
    companion object {
        fun of(review: Review): ReviewMainDto {
            return ReviewMainDto(
                youtubeUrl = review.youtubeUrl?.let { getLink(it) },
                title = review.title!!,
                star = review.star!!.toBigDecimal(),
                createDate = review.createdAt!!.toLocalDate().toString(),
                content = review.content!!
            )
        }

        private fun getLink(link: String): String {
            var newLink = link.run {
                this.replace("https://www.youtube.com/watch?v=", "")
                this.replace("https://youtu.be/", "")
            }

            newLink = if (newLink.isEmpty()) {
                "/image/site/no_img2.jpeg"
            } else {
                "https://i.ytimg.com/vi/$newLink/original.jpg"
            }

            return newLink
        }

    }
    @JvmName("getStar2")
    fun getStar(): BigDecimal {
        return this.star
    }
}