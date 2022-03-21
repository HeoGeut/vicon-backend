package com.vicon.viconbackend.features.contest

import com.fasterxml.jackson.databind.JsonNode
import com.fasterxml.jackson.databind.ObjectMapper
import com.vicon.viconbackend.config.SessionConst
import com.vicon.viconbackend.domain.board.BoardRepository
import com.vicon.viconbackend.domain.contest.*
import com.vicon.viconbackend.domain.member.MemberRepository
import com.vicon.viconbackend.domain.payment.Payment
import com.vicon.viconbackend.domain.payment.PaymentRepository
import com.vicon.viconbackend.features.auth.MemberService
import org.slf4j.LoggerFactory
import org.springframework.http.*
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.*
import java.math.BigDecimal
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.*
import javax.servlet.http.HttpServletRequest
import org.springframework.web.client.RestTemplate

@Controller
@RequestMapping("contests")
class ContestController(
    val contestService: ContestService,
    val memberService: MemberService,
    val memberRepository: MemberRepository,
    val boardRepository: BoardRepository,
    val paymentRepository: PaymentRepository,
) {
    private val logger = LoggerFactory.getLogger(this.javaClass)
    private val restTemplate = RestTemplate()
    private val objectMapper = ObjectMapper()

    @GetMapping("list")
    fun findAllByOpenState(model: Model): String {
        val contests = contestService.findTop9ByOpenContest()
        val contestDtoList = contests.map { ContestDTO.of(it) }
        model.addAttribute("contests", contestDtoList)

        return "contests/list"
    }

    @GetMapping("open")
    fun openContest(): String {
        return "contests/open"
    }

    @GetMapping("create1")
    fun createContest(
        @RequestParam(value = "type", required = true) type: String,
        model: Model,
    ): String {
        model.addAttribute("contestForm1", ContestCreateForm(c_type = type))

        return "contests/create1"
    }

    @PostMapping("create1")
    fun create1(
        model: Model,
        contestForm1: ContestCreateForm,
        request: HttpServletRequest,
    ): String {
        val memberId = request.session.getAttribute(SessionConst().LOGIN_MEMBER).toString()
        val username = memberService.findById(memberId.toLong()).get().username

        val contestForm2 = ContestCreateForm(
            username = username,
            c_type = contestForm1.c_type,
            businessCategory = contestForm1.businessCategory,
            title = contestForm1.title,
            name = contestForm1.name,
            text = contestForm1.text,
            style = contestForm1.style,
            orderNumber = LocalDateTime.now()
                .format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss")) + "_${contestForm1.c_type}",
        )

        model.addAttribute("contestForm2", contestForm2)

        return "contests/create2"
    }

    @PostMapping("create2")
    fun create2(
        model: Model,
        contestForm2: ContestCreateForm,
        request: HttpServletRequest,
    ): String {

        val contest = Contest().from(contestForm2)
        val memberId = request.session.getAttribute(SessionConst().LOGIN_MEMBER).toString()
        contest.member = memberService.findById(memberId.toLong()).get()
        contestService.save(contest)

        model.addAttribute("contestForm2", contestForm2)

        return "contests/payment"
    }

    @GetMapping("success")
    fun paymentSuccess(
        @RequestParam orderId: String,
        @RequestParam paymentKey: String,
        @RequestParam amount: BigDecimal,
        model: Model,
    ): String {
        val secretKey = "test_sk_zXLkKEypNArWmo50nX3lmeaxYG5R"
//        val secretKey = "live_sk_ZORzdMaqN3wQGwkpX2Mr5AkYXQGw"

        val headers = HttpHeaders()
        headers.set(
            "Authorization",
            "Basic " + Base64.getEncoder().encodeToString(("$secretKey:").toByteArray())
        )
        headers.contentType = MediaType.APPLICATION_JSON

        val payloadMap: MutableMap<String, String> = HashMap()
        payloadMap["orderId"] = orderId
        payloadMap["amount"] = java.lang.String.valueOf(amount)

        val request: HttpEntity<String> = HttpEntity(objectMapper.writeValueAsString(payloadMap), headers)

        val responseEntity: ResponseEntity<JsonNode> = restTemplate.postForEntity(
            "https://api.tosspayments.com/v1/payments/$paymentKey", request, JsonNode::class.java
        )

        return if (responseEntity.statusCode == HttpStatus.OK) {
            val successNode: JsonNode? = responseEntity.body
            logger.debug(successNode.toString())

            val contest = contestService.findByOrderNumber(orderId)
            val newPayment = Payment().from(successNode!!, contest)
            val savedPayment = paymentRepository.save(newPayment)
            val payment = PaymentDto.PaymentSuccessResponseDto.of(savedPayment)
            model.addAttribute("payment", payment)

            model.addAttribute("contestType", contest.type)
            val secret: String = successNode.get("secret").asText() // 가상계좌의 경우 입금 callback 검증을 위해서 secret을 저장하기를 권장함
            "contests/success"
        } else {
            val failNode: JsonNode? = responseEntity.body
            model.addAttribute("message", failNode!!.get("message").asText())
            model.addAttribute("code", failNode.get("code").asText())
            "contests/fail"
        }
    }

    @GetMapping("old")
    fun old(model: Model): String {
        val contests = contestService.findTop10ByCloseContest()
        val contestDtoList = contests.map { OldContestDTO.of(it) }
        model.addAttribute("contests", contestDtoList)

        return "contests/old"
    }

    @GetMapping("view/{id}")
    fun view(model: Model, @PathVariable id: String): String {
        val contest = contestService.findById(id.toLong()).get()
        val oldContestDTO = OldContestDTO.of(contest)

        model.addAttribute("contest", oldContestDTO)

        return "contests/view"
    }

    @GetMapping("case")
    fun case(model: Model): String {
        val contests = contestService.findTop3ByOrderByRecruitDeadLineDate()
        val contestDtoList = contests.map { ContestDTO.of(it) }
        model.addAttribute("contests", contestDtoList)

        val boards = boardRepository.findAll().toList()
        model.addAttribute("boards", boards)

        return "contests/case"
    }
}
