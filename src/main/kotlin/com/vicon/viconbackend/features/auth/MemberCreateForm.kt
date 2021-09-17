package com.vicon.viconbackend.features.auth

data class MemberCreateForm(
    val memberId: String = "",
    val memberPw: String = "",
    val memberPwCheck: String = "",

    val phoneNumberFront: String? = "",
    val phoneNumberMiddle: String? = "",
    val phoneNumberBack: String? = "",

    val emailFront: String? = "",
    val emailBack: String? = "",

    val companyName: String? = "",
    val businessCategory: String? = "",
    val websiteUrl: String? = "",

    val channelCategory: String? = "",
    val channelUrl: String? = "",
    val subscriberAmount: String? = "",

    val businessType: String = "",
    val channelType: List<String> = mutableListOf(),
)