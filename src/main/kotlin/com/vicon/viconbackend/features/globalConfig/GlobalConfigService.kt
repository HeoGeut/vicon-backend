package com.vicon.viconbackend.features.globalConfig

import com.vicon.viconbackend.domain.globalConfig.GlobalConfig
import com.vicon.viconbackend.domain.globalConfig.GlobalConfigRepository
import org.springframework.stereotype.Service

@Service
class GlobalConfigService(
    val globalConfigRepository: GlobalConfigRepository
) {
    fun save(globalConfig: GlobalConfig) = globalConfigRepository.save(globalConfig)
    fun findByConfigId(configId: String) = globalConfigRepository.findByConfigId(configId)
}