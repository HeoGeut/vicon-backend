package com.vicon.viconbackend.features.admin.auth

import com.vicon.viconbackend.config.SessionConst
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import javax.servlet.http.HttpServletRequest

@Controller
@RequestMapping("admin")
class AdminAuthController(
    val adminAuthService: AdminAuthService
) {

    @GetMapping("login")
    fun initialPage(): String {
        return "admin/auth/login"
    }

    @PostMapping("login")
    fun login(
        @RequestParam(value = "userid") id: String,
        @RequestParam(value = "password") pw: String,
        request: HttpServletRequest,
        model: Model
    ): String {
        val adminMember = adminAuthService.login(id, pw)
        if (adminMember == null) {
            val msg = "아이디 또는 비밀번호가 맞지 않습니다"
            model.addAttribute("msg", msg)
            model.addAttribute("url", "/admin/login")
            return "redirect"
        }

        val session = request.getSession(true)
        session.setAttribute(SessionConst().ADMIN_MEMBER, adminMember.configId)

        return "admin/index"
    }
}