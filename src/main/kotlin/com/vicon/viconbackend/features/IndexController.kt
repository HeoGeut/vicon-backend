package com.vicon.viconbackend.features

import com.vicon.viconbackend.domain.board.BoardRepository
import com.vicon.viconbackend.domain.member.Member
import com.vicon.viconbackend.domain.member.MemberRepository
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
import org.springframework.web.bind.annotation.SessionAttribute

@Controller
@RequestMapping("")
class IndexController(
    val contestService: ContestService,
    val memberService: MemberService,
    val memberRepository: MemberRepository,
    val boardRepository : BoardRepository,
    val reviewRepository: ReviewRepository
) {
    @GetMapping("/")
    fun index(
        model: Model,
        @SessionAttribute(name="loginMember", required = false) loginMemberId: String?
    ): String {

        val contests = contestService.findTop9ByOpenContest()
        val contestDtoList = contests.map { ContestDTO.of(it) }

        val members = memberService.findTop9By()
        val memberDtoList = members.map { MemberDTO.of(it) }

        val boards = boardRepository.findTop3By()
        val boardDtoList = boards.map { BoardDTO.of(it) }

        val reviews = reviewRepository.findTop3By()
        val reviewDtoList = reviews.map { ReviewMainDto.of(it) }

        model.addAttribute("contests", contestDtoList)
        model.addAttribute("members", memberDtoList)
        model.addAttribute("boards", boardDtoList)
        model.addAttribute("reviews", reviewDtoList)

        if (loginMemberId != null) {
            model.addAttribute("loginMember", loginMemberId)
        }

        return "index"
    }
}