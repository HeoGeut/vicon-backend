package com.vicon.viconbackend.features.mypage

import com.vicon.viconbackend.config.SessionConst
import com.vicon.viconbackend.domain.contest.ContestRepository
import com.vicon.viconbackend.features.apply.ApplyService
import com.vicon.viconbackend.features.auth.MemberService
import com.vicon.viconbackend.features.contest.ContestDTO
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.*
import javax.servlet.http.HttpServletRequest

@Controller
@RequestMapping("mypage")
class MypageController(
    val memberService: MemberService,
    val contestRepository: ContestRepository,
    val applyService: ApplyService
) {

    private val logger = LoggerFactory.getLogger(this.javaClass)

    @GetMapping("info")
    fun mypage(
        model: Model,
        request: HttpServletRequest
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

    @GetMapping("history")
    fun history(
        model: Model,
        request: HttpServletRequest
    ): String {
        val memberId = request.session.getAttribute(SessionConst().LOGIN_MEMBER).toString()
        val member = memberService.findById(memberId.toLong()).get()

        val contestHistoryDTO = member.contests!!.map { ContestHistoryDTO.from(it) }
        model.addAttribute("contestHistory", contestHistoryDTO)

        val contests = contestRepository.findTop3ByOrderByRecruitDeadLineDate()
        val contestDtoList = contests.map { ContestDTO.of(it) }
        model.addAttribute("contests", contestDtoList)

        return "mypage/history"
    }

    @PostMapping("ajaxApply")
    @ResponseBody
    fun applyAjax(
        @RequestBody id: String
    ): ApplyDTO {
        val apply = applyService.findById(id.dropLast(1).toLong()).get()
        return ApplyDTO.from(apply)
    }

    @PostMapping("ajaxApplyConfirm")
    @ResponseBody
    fun ajaxConfirm(
        @RequestBody id: String
    ): Int {
        val findApply = applyService.findById(id.dropLast(1).toLong()).get()
        return applyService.switchConfirm(findApply)
    }

    @PostMapping("ajaxComplete")
    @ResponseBody
    fun ajaxComplete(
        @RequestBody id: String
    ): Int {
        val findContest = contestRepository.findById(id.dropLast(1).toLong()).get()
        return try {
            findContest.isCompletedContents = true
            contestRepository.save(findContest)
            1
        } catch (e: Exception) {
            0
        }
    }

    @GetMapping("join")
    fun join(
        model: Model,
        request: HttpServletRequest
    ): String {
        val memberId = request.session.getAttribute(SessionConst().LOGIN_MEMBER).toString()
        val member = memberService.findById(memberId.toLong()).get()

        val contestHistories = member.contests!!.map { ContestJoinHistoryDto.of(it) }
        model.addAttribute("contestHistories", contestHistories)

        val contests = contestRepository.findTop3ByOrderByRecruitDeadLineDate()
        val contestDtoList = contests.map { ContestDTO.of(it) }
        model.addAttribute("contests", contestDtoList)

        return "mypage/join"
    }
}