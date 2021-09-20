package com.vicon.viconbackend.features.contest

import java.time.LocalDateTime

data class ContestCreateForm2 (
    val contestId: String = "",
    val c_reward: String = "",
    val c_ad_chk: String = "",
    val c_ad_price: String = "",
    val burdenFee: Boolean = false,
    val c_deadline: String? = "",
    val c_duedate: String? = "",
    val totalReward: String = ""
)