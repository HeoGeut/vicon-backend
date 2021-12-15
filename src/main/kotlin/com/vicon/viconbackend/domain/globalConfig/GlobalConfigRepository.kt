package com.vicon.viconbackend.domain.globalConfig

import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface GlobalConfigRepository : JpaRepository<GlobalConfig, Long>{
    fun findByConfigId(configId : String) : Optional<GlobalConfig>

}