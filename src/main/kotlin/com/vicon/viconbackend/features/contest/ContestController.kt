package com.vicon.viconbackend.features.contest

import com.vicon.viconbackend.domain.contest.*
import com.vicon.viconbackend.domain.member.MemberRepository
import org.joda.time.format.DateTimeFormatter
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.*
import java.time.LocalDateTime

@Controller
@RequestMapping("contests")
class ContestController(
    val contestService: ContestService,
    val memberRepository: MemberRepository
) {
    //    init {
//        val contest = Contest(
//            type = ContestType.STANDARD,
//            category = "기업 홍보",
//            title = "강서구 사회적기업 홍보",
//            name = "도라지 정과",
//            text = "강서구에 있는 도라지 정과 업체입니다.\n" +
//                    "\n" +
//                    "저희 제품의 장점은 haccp 인증 받은 제품이며 모두 진공 포장상태의 6개월 이상의 보관 기간과 정성을 다한 포장으로 선물용으로 제격인 제품입니다.\n" +
//                    "\n" +
//                    "스타트업 기업이기에 홍보비용에 한계가 있어 유튜브 영상을 통해 제품 및 기업 홍보를 함께 하고자 합니다.\n" +
//                    "\n" +
//                    "저희 회사가 소수의 인원이 함께 하고 있으나 한분한분이 모두 정성을 다해 제품생산에 땀을 흘리고 계십니다.\n" +
//                    "\n" +
//                    "이분들과 함께 좋은 영상 제작을 희망하며, 좋은 아이디어로 재미있는 영상 제작이 가능하신분들은 언제나 환영합니다.\n" +
//                    "\n" +
//                    "많이 참여해 주시기 바랍니다~",
//            contentsStyle = ContentsStyle.REVIEW,
//            reward = BigDecimal(1000000),
//            isPaidAds = false,
//            adsPrice = "300000원",
//            isBurdenFee = true,
//            recruitDeadLineDate = LocalDateTime.of(2021, 9, 30, 0, 0, 0),
//            contentsCompletedDate = LocalDateTime.of(2021, 10, 30, 0, 0, 0),
//            cashReceiptType = CashReceiptType.DEDUCTION,
//            cashReceiptIssuanceType = '1',
//            cashReceiptsNumber = "123123123",
//            isIssuedTaxInvoice = false,
//            taxInvoiceNumber = "789789879",
//            isConfirmed = true,
//            isPaidReward = false,
//            isCompletedContents = false,
//
//            totalPaymentPrice = BigDecimal(561000),
//            orderNumber = "20210731112721_2_25"
//        )
//
//        val savedContest = contestRepository.save(contest)
//    }
    //    init {
//        val member = Member(
//            profileImage = "https://vicon-static-bucket.s3.ap-northeast-2.amazonaws.com/user/saneone/profile_image/syukharbang.jpg",
//            memberId = "seonyul",
//            memberPw = "123123",
//            name = "선율샵",
//            phoneNumberFront = "010",
//            phoneNumberMiddle = "2706",
//            phoneNumberBack = "7170",
//            emailFront = "subid1",
//            emailBack = "naver.com",
//            companyName = "선율리틀샵",
//            businessCategory = "전자",
//            websiteurl = null,
//            channelCategory = "유튜브",
//            channelUrl = null,
//            SubscriberAmount = "100만",
//            businessType = BusinessType.INDIVIDUAL,
//            channelType = "1",
//            businessNumber = "123123123"
//        )
//        memberRepository.save(member)
//    }

    @GetMapping("list")
    fun findAllByOpenState(model: Model): String {
        val contests = contestService.findTop6ByOpenContest()
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
        val typeEnum = when (type) {
            "1" -> ContestType.STANDARD
            else -> ContestType.PREMIUM
        }

        val contest = Contest(type = typeEnum)
        val savedContestId = contestService.save(contest)

        model.addAttribute("id", savedContestId)
        model.addAttribute("type", type)
        model.addAttribute("contestForm1", ContestCreateForm1())

        return "contests/create1"
    }

    @PostMapping("create1")
    fun create1(
        @RequestParam id: String,
        model: Model,
        contestForm1: ContestCreateForm1
    ): String {

        val foundContest = contestService.findById(id.toLong()).get()
        foundContest.run {
            this.category = contestForm1.businessCategory
            this.title = contestForm1.title
            this.name = contestForm1.name
            this.text = contestForm1.text
            this.contentsStyle = contestForm1.contentsStyle
            this.type = contestForm1.tempContestType.run {
                if (this == "1") {
                    ContestType.STANDARD
                } else {
                    ContestType.PREMIUM
                }
            }
            this.orderNumber =
                LocalDateTime.now().format(java.time.format.DateTimeFormatter.ofPattern("yyyyMMddHHmm")) +
                        "_${type!!.ordinal}_${id}"
        }

        val savedContestId = contestService.save(foundContest)

        model.addAttribute("id", savedContestId)
        model.addAttribute("contestForm2", ContestCreateForm2())

        return "contests/create2"
    }

    @PostMapping("create2")
    fun create2(
        @RequestParam id: String,
        model: Model,
        contestForm2: ContestCreateForm2
    ): String {

        val foundContest = contestService.findById(id.toLong()).get()
        foundContest.run {
            this.reward = contestForm2.reward.toBigDecimal()
            this.isPaidAds = contestForm2.paidAds
            this.adsPrice = contestForm2.adsPrice
            this.isBurdenFee = contestForm2.burdenFee
            this.recruitDeadLineDate = contestForm2.recruitDeadLineDate
            this.contentsCompletedDate = contestForm2.contentsCompletedDate
            this.totalPaymentPrice = contestForm2.totalReward.toBigDecimal()
        }
        contestService.save(foundContest)

        model.addAttribute("contest", foundContest)

        return "contests/payment"
    }

    @GetMapping("payment")
    fun payment(): String {


        return "redirect:/"
    }

}
