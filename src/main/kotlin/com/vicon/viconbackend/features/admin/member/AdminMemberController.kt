package com.vicon.viconbackend.features.admin.member

import com.vicon.viconbackend.features.admin.AdminIndexService
import com.vicon.viconbackend.features.auth.MemberService
import org.slf4j.LoggerFactory
import org.springframework.data.domain.PageRequest
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.*
import javax.servlet.http.HttpServletRequest

@Controller
@RequestMapping("admin/member")
class AdminMemberController(
    val memberService: MemberService,
    val adminIndexService: AdminIndexService
) {
    private val logger = LoggerFactory.getLogger(this.javaClass)

    @GetMapping("")
    fun list(
        model: Model,
        request: HttpServletRequest,
        @RequestParam(value = "page_now", required = false, defaultValue = "1") selectedPage: String
    ): String {
        val pageRequest = PageRequest.of(selectedPage.toInt() - 1, 10)
        val page = memberService.findAllByPageable(pageRequest)
        val members = page.content.map { AdminMemberDTO.of(it) }
        model.addAttribute("members", members)
        model.addAttribute("totalPage", page.totalPages)
        model.addAttribute("currentPage", page.number + 1)

        val navigationName = adminIndexService.selectedNavigation(request.requestURI)
        model.addAttribute("navigation", navigationName)

        return "admin/member/list"
    }

    @PostMapping("ajax")
    @ResponseBody
    fun ajaxDelete(
        @RequestBody data: String
    ): Int {
        val returnValue = memberService.delete(data.dropLast(1))
        return if (returnValue) 1 else 0
    }

    @GetMapping("edit")
    fun edit(
        @RequestParam id: String,
        model: Model
    ): String {
        val member = memberService.findById(id.toLong()).get()
        val memberDetail = AdminMemberDetailDTO.of(member)
        model.addAttribute("member", memberDetail)

        return "admin/member/edit"
    }

    @PostMapping("edit")
    fun editMember(
        editForm: AdminMemberDetailDTO,
        model: Model
    ): String {
        val memberId = editForm.id
        val findMember = memberService.findById(memberId.toLong())
        if (findMember.isPresent) {
            val editedMember = findMember.get().fromEdit(editForm)
            return try {
                memberService.save(editedMember)
                val msg = "저장되었습니다"
                model.addAttribute("msg", msg)
                model.addAttribute("url", "/admin/member/edit?id=${memberId}")
                "redirect"
            } catch (e: Exception) {
                val msg = "DB오류입니다. 다시 시도해주세요\n${e.message}"
                model.addAttribute("msg", msg)
                model.addAttribute("url", "/admin/member")
                "redirect"
            }
        } else {
            val msg = "존재하지 않는 회원입니다. 잠시 후 다시 시도해주세요"
            model.addAttribute("msg", msg)
            model.addAttribute("url", "/admin/member")
            return "redirect"
        }
    }
}