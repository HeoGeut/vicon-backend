package com.vicon.viconbackend.domain.event

import com.vicon.viconbackend.domain.common.Auditable
import java.time.LocalDateTime
import javax.persistence.Entity

@Entity
data class Event(
    var title: String? = "",
    var startDate: String? = "",
    var endDate: LocalDateTime? = null,
    var content: LocalDateTime? = null,
    var file: String? = "",
    var fileName: String? = "",
    var img: String? = ""

) : Auditable<Long>()