package com.vicon.viconbackend.domain.globalConfig

import com.vicon.viconbackend.domain.common.Auditable
import javax.persistence.Entity

@Entity
data class GlobalConfig(
    var subjectEng: String? = "",
    var subjectKor: String? = "",
    var content: String? = "",
    var configId: String? = "",
    var configPw: String? = "",
    var configName: String? = ""
) : Auditable<Long>()