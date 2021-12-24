package com.vicon.viconbackend.features.admin.qna

import com.vicon.viconbackend.features.admin.AdminIndexService
import com.vicon.viconbackend.features.admin.contest.ToggleDTO
import com.vicon.viconbackend.features.auth.MemberService
import org.springframework.data.domain.PageRequest
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.*
import javax.servlet.http.HttpServletRequest

@Controller
@RequestMapping("admin/qna")
class AdminQnaController(
    val qnaService: AdminQnaService,
    val adminIndexService: AdminIndexService,
    val memberService: MemberService
) {
    @GetMapping("")
    fun list(
        model: Model,
        request: HttpServletRequest,
        @RequestParam(value = "page_now", required = false, defaultValue = "1") selectedPage: String
    ): String {
        val pageRequest = PageRequest.of(selectedPage.toInt() - 1, 10)
        val page = qnaService.findAllByPageable(pageRequest)
        val qnaList = page.content.map { AdminQnaDTO.of(it) }
        model.addAttribute("qnaList", qnaList)
        model.addAttribute("totalPage", page.totalPages)
        model.addAttribute("currentPage", page.number + 1)

        val navigationName = adminIndexService.selectedNavigation(request.requestURI)
        model.addAttribute("navigation", navigationName)

        return "admin/qna/list"
    }

    @PostMapping("ajax")
    @ResponseBody
    fun ajaxDelete(
        @RequestBody data: String
    ): Int {
        val returnValue = qnaService.delete(data.dropLast(1))
        return if (returnValue) 1 else 0
    }

    @PostMapping("toggleAjax")
    @ResponseBody
    fun ajaxToggle(
        toggleData: ToggleDTO
    ): Int {
        return try {
            val findQna = qnaService.findById(toggleData.id.toLong()).get()
            findQna.isConfirmed = !findQna.isConfirmed!!
            qnaService.save(findQna)
            1
        } catch (e: Exception) {
            0
        }
    }

    @GetMapping("view")
    fun view(
        @RequestParam id: String,
        model: Model
    ): String {
        val findQna = qnaService.findById(id.toLong()).get()
        val qna = AdminQnaDTO.of(findQna)
        model.addAttribute("qna", qna)
        model.addAttribute("memberId", memberService.findByUsername(qna.username).get().id.toString())

        return "admin/qna/view"
    }

    @PostMapping("view")
    fun answer(
        form: AdminQnaDTO,
        model: Model
    ): String {
        val findQna = qnaService.findById(form.id.toLong()).get()
        findQna.run {
            this.answer = form.answer
            this.isConfirmed = true
        }
        return try {
            qnaService.save(findQna)
            val msg = "등록되었습니다"
            model.addAttribute("msg", msg)
            model.addAttribute("url", "/admin/qna")
            "redirect"
        } catch (e: Exception) {
            val msg = "DB오류입니다. 다시 시도해주세요\n${e.message}"
            model.addAttribute("msg", msg)
            model.addAttribute("url", "/admin/qna")
            "redirect"
        }
    }
}