package com.vicon.viconbackend.features.reivew

import com.vicon.viconbackend.config.SessionConst
import com.vicon.viconbackend.domain.contest.ContestRepository
import com.vicon.viconbackend.domain.review.ReviewRepository
import com.vicon.viconbackend.features.auth.MemberService
import com.vicon.viconbackend.features.contest.ContestDTO
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import javax.servlet.http.HttpServletRequest

@Controller
@RequestMapping("review")
class ReviewController(
    val reviewRepository: ReviewRepository,
    val memberService: MemberService,
    val contestRepository : ContestRepository,
) {

    @GetMapping("list")
    fun list(model: Model): String {
        val reviews = reviewRepository.findTop5By()
        val reviewInfoDtoList =
            reviews.map { ReviewDto.ReviewResponseDto.of(it) }
        model.addAttribute("reviews", reviewInfoDtoList)

        return "review/list"
    }

    @GetMapping("view/{id}")
    fun view(model: Model, @PathVariable id: String): String {
        val review = reviewRepository.findById(id.toLong()).get()
        val reviewDto = ReviewDto.ReviewDetailsResponseDto.of(review)
        model.addAttribute("review", reviewDto)

        val contests = contestRepository.findTop3ByOrderByRecruitDeadLineDate()
        val contestDtoList = contests.map { ContestDTO.of(it) }
        model.addAttribute("contests", contestDtoList)

        return "review/view"
    }
}