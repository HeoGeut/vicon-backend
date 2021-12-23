package com.vicon.viconbackend.features.admin.creator

import com.vicon.viconbackend.features.admin.AdminIndexService
import com.vicon.viconbackend.features.auth.MemberService
import org.springframework.data.domain.PageRequest
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.*
import javax.servlet.http.HttpServletRequest

@Controller
@RequestMapping("admin/creator")
class AdminCreatorController(
    val memberService: MemberService,
    val adminIndexService: AdminIndexService
) {
    @GetMapping("")
    fun list(
        model: Model,
        request: HttpServletRequest,
        @RequestParam(value = "page_now", required = false, defaultValue = "1") selectedPage: String
    ): String {
        val pageRequest = PageRequest.of(selectedPage.toInt() - 1, 10)
        val page = memberService.findAllByPageable(pageRequest)
        val creators = page.content.map { AdminCreatorDTO.of(it) }
        model.addAttribute("creators", creators)
        model.addAttribute("totalPage", page.totalPages)
        model.addAttribute("currentPage", page.number + 1)

        val navigationName = adminIndexService.selectedNavigation(request.requestURI)
        model.addAttribute("navigation", navigationName)

        return "admin/creator/list"
    }

    @PostMapping("ajaxDelete")
    @ResponseBody
    fun ajaxDelete(
        @RequestBody data: String
    ): Int {
        val returnValue = memberService.delete(data.dropLast(1))
        return if (returnValue) 1 else 0
    }

    @GetMapping("view")
    fun view(
        @RequestParam id: String,
        model: Model
    ): String {
        val creator = memberService.findById(id.toLong()).get()
        val creatorDetail = AdminCreatorDetailDTO.of(creator)
        model.addAttribute("creator", creatorDetail)

        return "admin/creator/view"
    }
}