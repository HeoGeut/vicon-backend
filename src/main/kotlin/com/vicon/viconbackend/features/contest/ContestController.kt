package com.vicon.viconbackend.features.contest

import com.vicon.viconbackend.domain.board.BoardRepository
import com.vicon.viconbackend.domain.contest.*
import com.vicon.viconbackend.domain.member.Member
import com.vicon.viconbackend.domain.member.MemberRepository
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.*
import java.math.BigDecimal
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.temporal.ChronoUnit
import java.time.temporal.TemporalAmount

@Controller
@RequestMapping("contests")
class ContestController(
    val contestService: ContestService,
    val memberRepository: MemberRepository,
    val boardRepository: BoardRepository,
) {
    @GetMapping("list")
    fun findAllByOpenState(model: Model): String {
        val contests = contestService.findTop6ByOpenContest()
        val contestDtoList = contests.map { ContestDTO.of(it) }
        model.addAttribute("contests", contestDtoList)

        return "contests/list"
    }

    @GetMapping("open")
    fun openContest(): String {
        return "contests/open"
    }

    @GetMapping("create1")
    fun createContest(
        @RequestParam(value = "type", required = true) type: String,
        model: Model,
    ): String {
        model.addAttribute("c_type", type)
        model.addAttribute("contestForm1", ContestCreateForm())

        return "contests/create1"
    }

    @PostMapping("create1")
    fun create1(
        model: Model,
        contestForm1: ContestCreateForm
    ): String {
        val contestForm2 = ContestCreateForm(
            c_type = contestForm1.c_type,
            businessCategory = contestForm1.businessCategory,
            title = contestForm1.title,
            name = contestForm1.name,
            text = contestForm1.text,
            style = contestForm1.style,
        )
        model.addAttribute("contestForm2", contestForm2)

        return "contests/create2"
    }

    @PostMapping("create2")
    fun create2(
        model: Model,
        contestForm2: ContestCreateForm
    ): String {
        model.addAttribute("contestForm2", contestForm2)

        return "contests/payment"
    }

    @PostMapping("payment")
    fun payment(
        contestForm: ContestCreateForm
    ): String {
        val contest = Contest().from(contestForm)
        contestService.save(contest)

        return "redirect:/"
    }

    @GetMapping("old")
    fun old(model: Model): String {
        val contests = contestService.findTop10ByCloseContest()
        val contestDtoList = contests.map { OldContestDTO.of(it) }
        model.addAttribute("contests", contestDtoList)

        return "contests/old"
    }

    @GetMapping("view/{id}")
    fun view(model: Model, @PathVariable id: String): String {
        val contest = contestService.findById(id.toLong()).get()
        val oldContestDTO = OldContestDTO.of(contest)

        model.addAttribute("contest", oldContestDTO)

        return "contests/view"
    }

    @GetMapping("case")
    fun case(model: Model): String {
        val contests = contestService.findTop3ByOrderByRecruitDeadLineDate()
        val contestDtoList = contests.map { ContestDTO.of(it) }
        model.addAttribute("contests", contestDtoList)

        val boards = boardRepository.findAll().toList()
        model.addAttribute("boards", boards)

        return "contests/case"
    }
}
