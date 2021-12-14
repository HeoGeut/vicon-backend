package com.vicon.viconbackend.features.auth

import javax.validation.constraints.NotEmpty

data class LoginDTO(
    @NotEmpty
    val memberId: String = "",

    @NotEmpty
    val memberPw: String = ""
)