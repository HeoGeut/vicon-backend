package com.vicon.viconbackend

import com.vicon.viconbackend.domain.contest.*
import com.vicon.viconbackend.domain.globalConfig.GlobalConfig
import com.vicon.viconbackend.domain.member.Member
import com.vicon.viconbackend.features.auth.MemberService
import com.vicon.viconbackend.features.contest.ContestService
import com.vicon.viconbackend.features.globalConfig.GlobalConfigService
import org.springframework.stereotype.Component
import java.math.BigDecimal
import java.time.LocalDateTime
import javax.annotation.PostConstruct
import kotlin.random.Random

@Component
class PostConstructor(
    val memberService: MemberService,
    val globalConfigService: GlobalConfigService,
    val contestService: ContestService
) {

//    @PostConstruct
    fun initSetting() {
        setMembers()
        setGlobalConfig()
        setContests()
    }

    private fun setContests() {
        val contests = IntRange(1, 35).map {
            Contest(
                type = ContestType.STANDARD,
                category = "cate$it",
                channelType = ChannelType.FACEBOOK,
                title = "title$it",
                name = "name$it",
                text = "contest test text $it",
                contentsStyle = ContentsStyle.PPL,
                reward = BigDecimal(1000000),
                isPaidAds = false,
                adsPrice = BigDecimal(300000),
                isBurdenFee = false,
                recruitDeadLineDate = LocalDateTime.now(),
                contentsCompletedDate = LocalDateTime.now(),
                recruitNumber = (1..5).random().toBigDecimal(),
                cashReceiptType = CashReceiptType.UNISSUED,
                cashReceiptIssuanceType = (0..1).random().toString(),
                cashReceiptsNumber = "01012341234",
                isIssuedTaxInvoice = false,
                taxInvoiceNumber = "123123-123123",
                isConfirmed = Random.nextBoolean(),
                isPaidReward = Random.nextBoolean(),
                isCompletedContents = Random.nextBoolean(),
                totalPaymentPrice = BigDecimal(1111111),
                orderNumber = null,
                member = memberService.findById(it.toLong()).get(),
                enabled = true
            )
        }
        contestService.saveAll(contests)
    }

    private fun setGlobalConfig() {
        val globalConfig = GlobalConfig(
            configId = "test1",
            configPw = "qqq",
            configName = "testDev",
            content = "test content"
        )
        globalConfigService.save(globalConfig)
    }

    private fun setMembers() {
        val members = IntRange(1, 35).map {
            Member(
                username = "test$it",
                password = "qqq",
                phoneNumberFront = "010",
                phoneNumberMiddle = "7206",
                phoneNumberBack = "7170",
                emailFront = "subid$it",
                emailBack = "naver.com",
                companyName = "company$it",
                businessCategory = "cate$it",
                websiteUrl = "www.qqq.com",
                channelCategory = "cate$it",
                channelUrl = "www.qqq.com",
                subscriberAmount = "10만명",
                businessType = Member.BusinessType.values().random(),
                channelType = "유튜브",
                businessNumber = "126-123-123-13",
                isCertificated = false,
                enabled = true
            )
        }
        memberService.saveAll(members)
    }
}