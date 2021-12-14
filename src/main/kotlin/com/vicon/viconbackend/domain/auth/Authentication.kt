package com.vicon.viconbackend.domain.auth

data class AuthenticationRequest(
    var name: String = "",
    var username: String = "",
    var password: String = ""
)

data class AuthenticationPassword(
    var password: String = "",
    var changePassword: String = ""
)

data class AuthenticationResponse(
    var username: String = "",
    var token: String = "",
    var message: String = ""
)

data class AuthenticationLeave(
    var password: String = ""
)