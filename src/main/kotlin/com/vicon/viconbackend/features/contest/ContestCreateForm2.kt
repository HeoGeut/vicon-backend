package com.vicon.viconbackend.features.contest

import java.time.LocalDateTime

data class ContestCreateForm2 (
    val reward: String = "",
    val paidAds: Boolean = false,
    val adsPrice: String = "",
    val burdenFee: Boolean = false,
    val recruitDeadLineDate: LocalDateTime? = null,
    val contentsCompletedDate: LocalDateTime? = null,
    val totalReward: String = ""
)