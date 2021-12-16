package com.vicon.viconbackend.features.admin.member

import com.vicon.viconbackend.features.admin.AdminIndexService
import com.vicon.viconbackend.features.auth.MemberService
import org.springframework.data.domain.PageRequest
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import javax.servlet.http.HttpServletRequest

@Controller
@RequestMapping("admin/member")
class AdminMemberController(
    val memberService: MemberService,
    val adminIndexService: AdminIndexService
) {

    @GetMapping("")
    fun memberList(
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

        println(page.totalPages)
        println(page.number)

        val navigationName = adminIndexService.selectedNavigation(request.requestURI)
        model.addAttribute("navigation", navigationName)

        return "admin/member/list"
    }
}