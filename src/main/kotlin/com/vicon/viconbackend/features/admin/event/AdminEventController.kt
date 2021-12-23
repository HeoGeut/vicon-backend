package com.vicon.viconbackend.features.admin.event

import com.vicon.viconbackend.features.admin.AdminIndexService
import com.vicon.viconbackend.features.admin.creator.AdminCreatorDTO
import org.springframework.data.domain.PageRequest
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import javax.servlet.http.HttpServletRequest

//@Controller
//@RequestMapping("admin/event")
class AdminEventController(
    val eventService: AdminEventService,
    val adminIndexService: AdminIndexService
) {
    @GetMapping("")
    fun list(
        model: Model,
        request: HttpServletRequest,
        @RequestParam(value = "page_now", required = false, defaultValue = "1") selectedPage: String
    ) {
//        val pageRequest = PageRequest.of(selectedPage.toInt() - 1, 10)
//        val page = eventService.findAllByPageable(pageRequest)
//        val events = page.content.map { AdminCreatorDTO.of(it) }
//        model.addAttribute("creators", creators)
//        model.addAttribute("totalPage", page.totalPages)
//        model.addAttribute("currentPage", page.number + 1)
//
//        val navigationName = adminIndexService.selectedNavigation(request.requestURI)
//        model.addAttribute("navigation", navigationName)
//
//        return "admin/creator/list"
    }
}