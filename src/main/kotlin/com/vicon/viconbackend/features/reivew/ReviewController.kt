package com.vicon.viconbackend.features.reivew

import com.vicon.viconbackend.domain.review.ReviewRepository
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping

@Controller
@RequestMapping("review")
class ReviewController(
    val reviewRepository: ReviewRepository
) {

    @GetMapping("list")
    fun list(model: Model): String {
        val reviews = reviewRepository.findTop5By()
        val reviewInfoDtoList = reviews.map { ReviewInfoDTO.of(it) }
        model.addAttribute("reviews", reviewInfoDtoList)

        return "review/list"
    }
}