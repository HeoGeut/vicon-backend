package com.vicon.viconbackend.interceptor

import com.vicon.viconbackend.config.SessionConst
import org.springframework.web.servlet.HandlerInterceptor
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

class LoginCheckInterceptor : HandlerInterceptor {

    @Throws(Exception::class)
    override fun preHandle(request: HttpServletRequest, response: HttpServletResponse, handler: Any): Boolean {
        val requestURI = request.requestURI
        val session = request.session
        if (session?.getAttribute(SessionConst().LOGIN_MEMBER) == null) {
            //로그인으로 redirect
            response.sendRedirect("/auth/login?redirectURI=$requestURI")
            return false
        }
        return true
    }
}