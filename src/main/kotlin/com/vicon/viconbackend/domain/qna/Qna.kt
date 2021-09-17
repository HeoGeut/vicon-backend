package com.vicon.viconbackend.domain.qna

import com.vicon.viconbackend.domain.common.Auditable
import com.vicon.viconbackend.domain.member.Member
import javax.persistence.Entity
import javax.persistence.FetchType
import javax.persistence.ManyToOne

@Entity
data class Qna(
    var title: String? = "",
    var content: String? = "",
    var isConfirmed: Boolean? = false,
    var memo: String? = "",

    @ManyToOne(fetch = FetchType.LAZY)
    var member: Member? = null

) : Auditable<Long>()