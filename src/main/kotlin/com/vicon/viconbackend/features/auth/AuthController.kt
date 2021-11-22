package com.vicon.viconbackend.features.auth

import com.vicon.viconbackend.domain.member.Member
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.*

@Controller
@RequestMapping("auth")
class AuthController(
    val memberService: MemberService
) {

    @GetMapping("join")
    fun join(model: Model): String {
        model.addAttribute("memberCreateForm", MemberCreateForm())
        return "auth/join"
    }

    @PostMapping("join")
    fun createMember(memberCreateForm: MemberCreateForm): String {

//        println("=======================")
//        println(memberCreateForm)
//        println("=======================")

        val member = Member().from(memberCreateForm)
        memberService.save(member)
        return "redirect:/"
    }

    @PostMapping("ajax")
    @ResponseBody
    fun ajax(
        @RequestBody data: String
    ): Int {
        return if (memberService.findByMemberId(data).isPresent)
            1
        else 0
    }

    @GetMapping("login")
    fun login(model: Model): String {
        model.addAttribute("loginForm", LoginDTO())
        return "auth/login"
    }

    @PostMapping("loginAjax")
    @ResponseBody
    fun loginMember(
        @RequestParam("id") id: String,
        @RequestParam("pw") pw: String
    ): Int {
//        println("id : $id")
//        println("pw : $pw")

        val findMember = memberService.findByMemberId(id)
        val isExist = findMember.isPresent
//        println(isExist)

        return if (!isExist) {
            1
        } else {
            if (memberService.login(LoginDTO(id, pw))) {
                2
            } else {
                3
            }
        }
    }
}