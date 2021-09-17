package com.vicon.viconbackend.domain.member

import com.vicon.viconbackend.domain.common.Persistable
import javax.persistence.Entity
import javax.persistence.FetchType
import javax.persistence.ManyToOne

@Entity
data class MemberResetPassword(
    @ManyToOne(fetch = FetchType.LAZY)
    var member: Member? = null

) : Persistable<Long>()