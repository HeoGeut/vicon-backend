package com.vicon.viconbackend.features.admin.event

data class AdminEventDTO(
    val thumbnailImage: String = "",
    val title: String = "",
    val startDate: String = "",
    val endDate: String = "",
    val createdDate: String = ""
)