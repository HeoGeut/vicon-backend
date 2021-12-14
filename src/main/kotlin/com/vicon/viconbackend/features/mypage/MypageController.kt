package com.vicon.viconbackend.features.mypage

import com.vicon.viconbackend.config.SessionConst
import com.vicon.viconbackend.domain.contest.ContestRepository
import com.vicon.viconbackend.features.auth.MemberDTO
import com.vicon.viconbackend.features.auth.MemberService
import com.vicon.viconbackend.features.contest.ContestDTO
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import java.lang.reflect.Member
import javax.servlet.http.HttpServletRequest

@Controller
@RequestMapping("mypage")
class MypageController(
    val memberService : MemberService,
    val contestRepository: ContestRepository
) {

    @GetMapping("info")
    fun mypage(
        model : Model,
        request : HttpServletRequest
    ): String {

        val memberId = request.session.getAttribute(SessionConst().LOGIN_MEMBER).toString()
        val member = memberService.findById(memberId.toLong()).get()
        val memberInfoDTO = MemberInfoDTO.of(member)
        model.addAttribute("member", memberInfoDTO)

        val contests = contestRepository.findTop3ByOrderByRecruitDeadLineDate()
        val contestDtoList = contests.map { ContestDTO.of(it) }
        model.addAttribute("contests", contestDtoList)

        return "mypage/info"
    }
}