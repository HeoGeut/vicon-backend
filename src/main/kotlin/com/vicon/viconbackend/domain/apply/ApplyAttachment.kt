package com.vicon.viconbackend.domain.apply

import com.vicon.viconbackend.domain.common.Auditable
import javax.persistence.Entity
import javax.persistence.FetchType
import javax.persistence.JoinColumn
import javax.persistence.ManyToOne

@Entity
data class ApplyAttachment(
    var fileName: String? = "",
    var realName: String? = "",

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "APPLY_ID")
    var apply: Apply? = null

) : Auditable<Long>()