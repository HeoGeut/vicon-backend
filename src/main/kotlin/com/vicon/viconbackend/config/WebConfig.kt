package com.vicon.viconbackend.config

import com.vicon.viconbackend.interceptor.LoginCheckInterceptor
import org.springframework.context.annotation.Configuration
import org.springframework.web.servlet.config.annotation.InterceptorRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer

@Configuration
class WebConfig : WebMvcConfigurer {

    override fun addInterceptors(registry: InterceptorRegistry) {

        registry.addInterceptor(LoginCheckInterceptor())
            .order(1)
            .addPathPatterns("/**")
            .excludePathPatterns(
                "/",
                "/creator/**",
                "/event/**",
                "/info/**",
                "/main/**",
                "/review/**",
                "/auth/join", "/auth/login",
                "/contests/case", "/contests/list", "/contests/old", "/contests/open", "/contests/view",
                "/static/**", "/css/**", "/image/**", "/js/**", "/lib/**",
                "/*.ico", "/error",
//                "/login", "/logout"
            )
    }
}