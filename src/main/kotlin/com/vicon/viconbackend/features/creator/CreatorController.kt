package com.vicon.viconbackend.features.creator

import com.vicon.viconbackend.domain.contest.ContestRepository
import com.vicon.viconbackend.features.auth.MemberService
import com.vicon.viconbackend.features.contest.ContestDTO
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping

@Controller
@RequestMapping("creator")
class CreatorController(
    val memberService: MemberService,
    val contestRepository: ContestRepository
) {
    @GetMapping("list")
    fun list(model: Model) : String{
        val members = memberService.findTop10By()
        val creators = members.map { CreatorDTO.of(it) }
        model.addAttribute("creators", creators)

        val contests = contestRepository.findTop3ByOrderByRecruitDeadLineDate()
        val contestDtoList = contests.map { ContestDTO.of(it) }
        model.addAttribute("contests", contestDtoList)

        return "creator/list"
    }

}