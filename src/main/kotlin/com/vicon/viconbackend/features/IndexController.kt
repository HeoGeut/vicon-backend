package com.vicon.viconbackend.features

import com.vicon.viconbackend.domain.board.BoardRepository
import com.vicon.viconbackend.domain.review.ReviewRepository
import com.vicon.viconbackend.features.contest.*
import com.vicon.viconbackend.features.contest.ContestService
import com.vicon.viconbackend.features.auth.MemberDTO
import com.vicon.viconbackend.features.auth.MemberService
import com.vicon.viconbackend.features.board.BoardDTO
import com.vicon.viconbackend.features.reivew.ReviewMainDto
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping

@Controller
@RequestMapping("")
class IndexController(
    val contestService: ContestService,
    val memberService: MemberService,
    val boardRepository : BoardRepository,
    val reviewRepository: ReviewRepository
) {
    @GetMapping("")
    fun index(model: Model): String {
        val contests = contestService.findTop6ByOpenContest()
        val contestDtoList = contests.map { ContestDTO.of(it) }

        val members = memberService.findTop6By()
        val memberDtoList = members.map { MemberDTO.of(it) }

        val boards = boardRepository.findTop3By()
        val boardDtoList = boards.map { BoardDTO.of(it) }

        val reviews = reviewRepository.findTop3By()
        val reviewDtoList = reviews.map { ReviewMainDto.of(it) }

        model.addAttribute("contests", contestDtoList)
        model.addAttribute("members", memberDtoList)
        model.addAttribute("boards", boardDtoList)
        model.addAttribute("reviews", reviewDtoList)

        return "index"
    }
}