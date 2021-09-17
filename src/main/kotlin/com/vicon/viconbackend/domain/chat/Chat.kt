package com.vicon.viconbackend.domain.chat

import com.vicon.viconbackend.domain.apply.Apply
import com.vicon.viconbackend.domain.common.Auditable
import com.vicon.viconbackend.domain.member.Member
import javax.persistence.Entity
import javax.persistence.FetchType
import javax.persistence.ManyToOne

@Entity
data class Chat(
    var content: String? = "",

    @ManyToOne(fetch = FetchType.LAZY)
    var member: Member? = null,

    @ManyToOne(fetch = FetchType.LAZY)
    var apply: Apply? = null

) : Auditable<Long>()