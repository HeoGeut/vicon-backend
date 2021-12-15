package com.vicon.viconbackend.interceptor

import com.vicon.viconbackend.config.SessionConst
import org.springframework.web.servlet.HandlerInterceptor
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

class LoginCheckInterceptor : HandlerInterceptor {

    @Throws(Exception::class)
    override fun preHandle(request: HttpServletRequest, response: HttpServletResponse, handler: Any): Boolean {
        val requestURI = request.requestURI
        println(requestURI)

        if(requestURI.contains("admin")) return adminHandle(request, response)

        val session = request.session
        return if (session?.getAttribute(SessionConst().LOGIN_MEMBER) == null) {
            response.sendRedirect("/auth/login?redirectURI=$requestURI")
            false
        } else {
            true
        }
    }

    private fun adminHandle(request: HttpServletRequest, response: HttpServletResponse): Boolean {
        val session = request.session
        return if (session?.getAttribute(SessionConst().ADMIN_MEMBER) == null) {
            response.sendRedirect("/admin/login")
            false
        } else {
            true
        }
    }
}