package com.vicon.viconbackend.features.contest

import org.springframework.web.multipart.MultipartFile

data class ContestCreateForm(
    val username: String = "",
    val c_type: String = "",
    val businessCategory: String = "",
    val title: String = "",
    val name: String = "",
    val text: String = "",
    val style: String = "",
    val file: MultipartFile? = null,
    val recruitNumber: String = "",
    val c_reward: String = "",
    val c_ad_chk: String = "0",
    val c_ad_price: String? = "0",
    val burdenFee: String = "0",
    val c_deadline: String = "",
    val c_duedate: String = "",
    val totalReward: String = "",
    val enabled : String = "1",
    val orderNumber: String = "",
)