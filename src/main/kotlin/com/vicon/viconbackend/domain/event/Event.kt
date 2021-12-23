package com.vicon.viconbackend.domain.event

import com.vicon.viconbackend.domain.common.Auditable
import java.time.LocalDateTime
import javax.persistence.Entity

@Entity
data class Event(
    var title: String? = "",
    var startDate: LocalDateTime? = null,
    var endDate: LocalDateTime? = null,
    var content: String? = null,
    var file: String? = "",
    var fileName: String? = "",
    var img: String? = "",

    var enabled: Boolean

) : Auditable<Long>()