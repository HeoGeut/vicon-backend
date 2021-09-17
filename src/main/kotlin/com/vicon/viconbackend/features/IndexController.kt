package com.vicon.viconbackend.features

import com.vicon.viconbackend.features.contest.*
import com.vicon.viconbackend.features.contest.ContestService
import com.vicon.viconbackend.features.auth.MemberDTO
import com.vicon.viconbackend.features.auth.MemberService
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping

@Controller
@RequestMapping("")
class IndexController(
    val contestService: ContestService,
    val memberService: MemberService
) {
    @GetMapping("")
    fun index(model: Model): String {
        val contests = contestService.findTop6ByOpenContest()
        val contestDtoList = contests.map { ContestDTO.of(it) }

        val members = memberService.findTop6By()
        val memberDtoList = members.map { MemberDTO.of(it) }

        model.addAttribute("contests", contestDtoList)
        model.addAttribute("members", memberDtoList)

        return "index"
    }
}