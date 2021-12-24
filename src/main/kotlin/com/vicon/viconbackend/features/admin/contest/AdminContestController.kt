package com.vicon.viconbackend.features.admin.contest

import com.vicon.viconbackend.features.admin.AdminIndexService
import com.vicon.viconbackend.features.apply.ApplyService
import com.vicon.viconbackend.features.contest.ContestService
import org.slf4j.LoggerFactory
import org.springframework.data.domain.PageRequest
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.*
import javax.servlet.http.HttpServletRequest

@Controller
@RequestMapping("admin/contest")
class AdminContestController(
    val contestService: ContestService,
    val adminIndexService: AdminIndexService,
    val applyService: ApplyService
) {

    private val logger = LoggerFactory.getLogger(this.javaClass)

    @GetMapping("")
    fun contestList(
        model: Model,
        request: HttpServletRequest,
        @RequestParam(value = "page_now", required = false, defaultValue = "1") selectedPage: String
    ): String {
        val pageRequest = PageRequest.of(selectedPage.toInt() - 1, 10)
        val page = contestService.findAllByPageable(pageRequest)
        val contests = page.content.map { AdminContestDTO.of(it) }
        model.addAttribute("contests", contests)
        model.addAttribute("totalPage", page.totalPages)
        model.addAttribute("currentPage", page.number + 1)

        val navigationName = adminIndexService.selectedNavigation(request.requestURI)
        model.addAttribute("navigation", navigationName)

        return "admin/contest/list"
    }

    @PostMapping("ajax")
    @ResponseBody
    fun ajaxDelete(
        @RequestBody data: String // input text 에 = 붙어서 오는 버그 @RequestParam 쓰면 해결된다고함
    ): Int {
        val returnValue = contestService.delete(data.dropLast(1))
        return if (returnValue) 1 else 0
    }

    @PostMapping("toggleAjax")
    @ResponseBody
    fun ajaxToggle(
        toggleData: ToggleDTO
    ): Int {
        return try {
            val findContest = contestService.findById(toggleData.id.toLong()).get()
            if (toggleData.item == "certification") {
                findContest.isConfirmed = !findContest.isConfirmed!!
            } else {
                findContest.isPaidReward = !findContest.isPaidReward!!
            }
            contestService.save(findContest)
            1
        } catch (e: Exception) {
            0
        }
    }

    @GetMapping("view")
    fun contestView(
        @RequestParam id: String,
        model: Model
    ): String {
        val findContest = contestService.findById(id.toLong()).get()
        val contest = AdminContestViewDTO.of(findContest)
        model.addAttribute("contest", contest)

        return "admin/contest/view"
    }

    @PostMapping("applyAjax")
    @ResponseBody
    fun ajaxApply(
        @RequestParam applyId: String,
        model: Model
    ): ApplyDetailDTO {
        val findApply = applyService.findById(applyId.toLong()).get()
        return ApplyDetailDTO.of(findApply)
    }
}