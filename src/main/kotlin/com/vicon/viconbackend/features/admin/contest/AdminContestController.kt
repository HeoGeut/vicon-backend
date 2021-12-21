package com.vicon.viconbackend.features.admin.contest

import com.vicon.viconbackend.features.admin.AdminIndexService
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
    val adminIndexService: AdminIndexService
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
        @RequestBody data: String // input text에 = 붙어서 오는 버그 @RequestParam 쓰면 해결된다고함
    ): Int {
        val returnValue = contestService.delete(data.dropLast(1))
        return if (returnValue) 1 else 0
    }

    @PostMapping("toggleAjax")
    @ResponseBody
    fun ajaxToggle(
        toggleData: ToggleDTO
    ): Int {
        logger.debug(toggleData.toString())
        return try {
            val findContest = contestService.findById(toggleData.contestId.toLong()).get()
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
}