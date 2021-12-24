package com.vicon.viconbackend.domain.faq

import com.vicon.viconbackend.domain.common.Auditable
import javax.persistence.Entity

@Entity
data class Faq(
    var title: String? = "",
    var content: String? = "",

    var enabled: Boolean = true

) : Auditable<Long>()