package com.vicon.viconbackend.features.admin.faq

import com.vicon.viconbackend.domain.faq.Faq
import com.vicon.viconbackend.features.admin.AdminIndexService
import org.slf4j.LoggerFactory
import org.springframework.data.domain.PageRequest
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.*
import javax.servlet.http.HttpServletRequest

@Controller
@RequestMapping("admin/faq")
class AdminFaqController(
    val faqService: AdminFaqService,
    val adminIndexService: AdminIndexService
) {
    private val logger = LoggerFactory.getLogger(this.javaClass)

    @GetMapping("")
    fun list(
        model: Model,
        request: HttpServletRequest,
        @RequestParam(value = "page_now", required = false, defaultValue = "1") selectedPage: String
    ): String {
        val pageRequest = PageRequest.of(selectedPage.toInt() - 1, 10)
        val page = faqService.findAllByPageable(pageRequest)
        val faqList = page.content.toList()
        model.addAttribute("faqList", faqList)
        model.addAttribute("totalPage", page.totalPages)
        model.addAttribute("currentPage", page.number + 1)

        val navigationName = adminIndexService.selectedNavigation(request.requestURI)
        model.addAttribute("navigation", navigationName)

        return "admin/faq/list"
    }

    @PostMapping("ajax")
    @ResponseBody
    fun ajaxDelete(
        @RequestBody data: String
    ): Int {
        val returnValue = faqService.delete(data.dropLast(1))
        return if (returnValue) 1 else 0
    }

    @GetMapping("edit")
    fun edit(
        @RequestParam id: String,
        model: Model
    ): String {
        var faq = Faq()
        if (id != "0") {
            faq = faqService.findById(id.toLong()).get()
        }
        model.addAttribute("faq", faq)

        return "admin/faq/edit"
    }

    @PostMapping("edit")
    fun editMember(
        form: Faq,
        model: Model
    ): String {
        logger.debug(form.toString())

        var faq = Faq()
        if (!form.id?.toString().isNullOrEmpty()) {
            logger.debug("id is null or empty")
            faq = faqService.findById(form.id!!.toLong()).get()
        }
        return try {
            faq.run {
                this.title = form.title
                this.content = form.content
            }
            logger.debug(faq.toString())
            faqService.save(faq)

            val msg = "저장되었습니다"
            model.addAttribute("msg", msg)
            model.addAttribute("url", "/admin/faq")
            "redirect"
        } catch (e: Exception) {
            val msg = "DB오류입니다. 다시 시도해주세요\n${e.message}"
            model.addAttribute("msg", msg)
            model.addAttribute("url", "/admin/faq")
            "redirect"
        }

    }
}