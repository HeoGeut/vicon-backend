package com.vicon.viconbackend.features.auth

import com.vicon.viconbackend.config.SessionConst
import com.vicon.viconbackend.domain.member.Member
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.validation.BindingResult
import org.springframework.web.bind.annotation.*
import javax.servlet.http.HttpServletRequest

@Controller
@RequestMapping("auth")
class AuthController(
    val memberService: MemberService,
    val authService: AuthService
) {
    private val logger = LoggerFactory.getLogger(AuthController::class.java)

    @GetMapping("join")
    fun join(model: Model): String {
        val channelTypes : LinkedHashMap<String, String> = linkedMapOf()
        channelTypes["YOUTUBE"] = "유튜브"
        channelTypes["BLOG"] = "블로그"
        channelTypes["INSTAGRAM"] = "인스타그램"
        channelTypes["FACEBOOK"] = "페이스북"

        model.addAttribute("channelTypes", channelTypes)
        model.addAttribute("memberCreateForm", MemberCreateForm())
        return "auth/join"
    }

    @PostMapping("join")
    fun createMember(memberCreateForm: MemberCreateForm): String {

        val member = Member().from(memberCreateForm)
        memberService.save(member)
        return "redirect:/"
    }

    @PostMapping("ajax")
    @ResponseBody
    fun ajax(
        @RequestBody data: String
    ): Int {
        return if (memberService.findByUsername(data.dropLast(1)).isPresent)
            0
        else 1
    }

    @GetMapping("login")
    fun login(model: Model): String {
        model.addAttribute("loginForm", LoginDTO())
        return "auth/login"
    }

    @PostMapping("login")
    fun loginMember(
        @ModelAttribute loginDTO: LoginDTO,
        request: HttpServletRequest,
        bindingResult: BindingResult,
        @RequestParam(value = "redirectURI", defaultValue = "/") redirectURI: String,
        model: Model
    ): String {

        if (bindingResult.hasErrors()) {
            logger.debug("error")
            return "auth/login"
        }

        val loginMember = authService.login(loginDTO)

        if (loginMember == null) {
            val msg = "아이디 또는 비밀번호가 맞지 않습니다"
            model.addAttribute("msg", msg)
            model.addAttribute("url", "/auth/login")
            return "redirect"
        }

        val session = request.getSession(true)
        session.setAttribute(SessionConst().LOGIN_MEMBER, loginMember.id)

        return "redirect:$redirectURI"

    }

    @GetMapping("/logout")
    fun logout(request: HttpServletRequest): String? {
        val session = request.getSession(false)
        session?.invalidate()
        return "redirect:/"
    }

    @GetMapping("edit/{id}")
    fun edit(@PathVariable id: Long, model: Model): String {
        val member = memberService.findById(id).get()
        val memberDetails = MemberDetailsDto.of(member).also { println(it) }
        model.addAttribute("memberDetails", memberDetails)

        val channelTypes : LinkedHashMap<String, String> = linkedMapOf()
        channelTypes["YOUTUBE"] = "유튜브"
        channelTypes["BLOG"] = "블로그"
        channelTypes["INSTAGRAM"] = "인스타그램"
        channelTypes["FACEBOOK"] = "페이스북"
        model.addAttribute("channelTypes", channelTypes)

        return "auth/edit"
    }

    @PostMapping("edit")
    fun editConfirm(memberDetailsDto: MemberDetailsDto): String {
        memberDetailsDto.also { println(it) }
        val findMember = memberService.findById(memberDetailsDto.id).get()
        val newValue = memberDetailsDto.toEntity()
        findMember.update(newValue)
        memberService.save(findMember)

        return "redirect:/"
    }
}