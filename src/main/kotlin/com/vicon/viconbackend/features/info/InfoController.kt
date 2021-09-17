package com.vicon.viconbackend.features.info

import com.vicon.viconbackend.domain.contest.ContestRepository
import com.vicon.viconbackend.features.contest.ContestDTO
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping

@Controller
@RequestMapping("info")
class InfoController(
    val contestRepository: ContestRepository
) {

    @GetMapping("howto")
    fun howToUse(model: Model): String {
        val contests = contestRepository.findTop3ByOrderByRecruitDeadLineDate()
        val contestDtoList = contests.map { ContestDTO.of(it) }

        model.addAttribute("contests", contestDtoList)

        return "info/howto"
    }

    @GetMapping("faq")
    fun faq(model: Model): String {
        val contests = contestRepository.findTop3ByOrderByRecruitDeadLineDate()
        val contestDtoList = contests.map { ContestDTO.of(it) }

        model.addAttribute("contests", contestDtoList)

        return "info/faq"
    }
}