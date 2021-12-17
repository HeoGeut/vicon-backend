package com.vicon.viconbackend

import com.vicon.viconbackend.domain.globalConfig.GlobalConfig
import com.vicon.viconbackend.domain.member.Member
import com.vicon.viconbackend.features.auth.MemberService
import com.vicon.viconbackend.features.globalConfig.GlobalConfigService
import org.springframework.stereotype.Component

@Component
class PostConstructor(
    val memberService: MemberService,
    val globalConfigService: GlobalConfigService
) {

    //    @PostConstruct
    fun initSetting() {
        val memberNames = IntRange(1, 20).map { "test$it" }
        val members = memberNames.map {
            Member(
                username = it,
                password = "qqq",
                phoneNumberFront = "010",
                phoneNumberMiddle = "7206",
                phoneNumberBack = "7170",
                emailFront = "subid1",
                emailBack = "naver.com",
                companyName = "company1",
                businessCategory = "cate1",
                websiteUrl = "www.qqq.com",
                channelCategory = "cate1",
                channelUrl = "www.qqq.com",
                subscriberAmount = "10만명",
                businessType = Member.BusinessType.INDIVIDUAL,
                channelType = "유튜브",
                businessNumber = "126-123-123-13",
                isCertificated = false,
            )
        }
        memberService.saveAll(members)

        val globalConfig = GlobalConfig(
            configId = "test1",
            configPw = "qqq",
            configName = "testDev",
            content = "test content"
        )
        globalConfigService.save(globalConfig)
    }
}