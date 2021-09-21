package com.vicon.viconbackend.features.contest

import org.springframework.web.multipart.MultipartFile

data class ContestCreateForm (
        val type: String = "",
        val businessCategory: String = "",
        val title: String = "",
        val name: String = "",
        val text: String = "",
        val contentsStyle: String = "",
        val file: MultipartFile? = null,
        val c_reward: String = "",
        val c_ad_chk: String = "",
        val c_ad_price: String = "",
        val burdenFee: String = "",
        val c_deadline: String = "",
        val c_duedate: String = "",
        val totalReward: String = ""
)