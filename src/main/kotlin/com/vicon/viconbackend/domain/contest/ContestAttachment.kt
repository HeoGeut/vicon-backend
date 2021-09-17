package com.vicon.viconbackend.domain.contest

import com.vicon.viconbackend.domain.common.Persistable
import javax.persistence.Entity
import javax.persistence.FetchType
import javax.persistence.ManyToOne

@Entity
data class ContestAttachment(
    var fileName: String? = "",
    var realName: String? = "",
    var virtualNumber: String? = "",

    @ManyToOne(fetch = FetchType.LAZY)
    var contest: Contest? = null

) : Persistable<Long>()