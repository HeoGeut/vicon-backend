package com.vicon.viconbackend.features.admin.auth

import com.vicon.viconbackend.domain.globalConfig.GlobalConfig
import com.vicon.viconbackend.features.globalConfig.GlobalConfigService
import org.springframework.stereotype.Service

@Service
class AdminAuthService(
    val globalConfigService: GlobalConfigService
) {
    fun login(id: String, pw: String): GlobalConfig? {
        val findMember = globalConfigService.findByConfigId(id)
        return when (findMember.isPresent) {
            true -> {
                if (pw == findMember.get().configPw) findMember.get() else null
            }
            false -> null
        }
    }
}