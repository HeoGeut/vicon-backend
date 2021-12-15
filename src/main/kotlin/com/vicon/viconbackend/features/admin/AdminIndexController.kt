package com.vicon.viconbackend.features.admin

import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import javax.servlet.http.HttpServletRequest

@Controller
@RequestMapping("admin")
class AdminIndexController(
    val adminIndexService: AdminIndexService
) {

    @GetMapping("")
    fun index(
        model: Model,
        request: HttpServletRequest
    ): String {
        val navigationName = adminIndexService.selectedNavigation(request.requestURI)
        model.addAttribute("navigation", navigationName)
        return "admin/index"
    }
}