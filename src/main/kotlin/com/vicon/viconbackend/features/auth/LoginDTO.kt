package com.vicon.viconbackend.features.auth

data class LoginDTO(
    val memberId: String = "",
    val memberPw: String = ""
)