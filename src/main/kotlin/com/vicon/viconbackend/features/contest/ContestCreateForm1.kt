package com.vicon.viconbackend.features.contest

import com.vicon.viconbackend.domain.contest.ContentsStyle
import org.springframework.web.multipart.MultipartFile

data class ContestCreateForm1(
    val contestId: String = "",
    val businessCategory: String = "",
    val title: String = "",
    val name: String = "",
    val text: String = "",
    val contentsStyle: ContentsStyle? = ContentsStyle.PPL,
    val file: MultipartFile? = null,
    val tempContestType: String = "",
)