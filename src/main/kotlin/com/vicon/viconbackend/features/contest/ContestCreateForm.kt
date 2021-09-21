package com.vicon.viconbackend.features.contest

import com.vicon.viconbackend.domain.contest.ContentsStyle
import org.springframework.web.multipart.MultipartFile

data class ContestCreateForm (
    val c_type: String = "",
    val businessCategory: String = "",
    val title: String = "",
    val name: String = "",
    val text: String = "",
    val c_style: String = "",
    val file: MultipartFile? = null,
    val c_reward: String = "",
    val c_ad_chk: String = "",
    val c_ad_price: String = "",
    val burdenFee: String = "",
    val c_deadline: String = "",
    val c_duedate: String = "",
    val totalReward: String = ""
)