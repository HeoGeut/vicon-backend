package com.vicon.viconbackend.domain.apply

import com.vicon.viconbackend.domain.common.Auditable
import com.vicon.viconbackend.domain.contest.Contest
import com.vicon.viconbackend.domain.member.Member
import javax.persistence.*

@Entity
data class Apply(
    var channelInfo: String? = "",
    var successStory: String? = "",
    var storyboardText: String? = "",
    var storyboardDraw: String? = "",
    var expectEffect: String? = "",

    var isConfirm: Boolean? = false,

    @OneToMany(mappedBy = "apply", cascade = [CascadeType.ALL])
    var applyAttachments: List<ApplyAttachment> = mutableListOf(),

    @OneToMany(mappedBy = "apply", cascade = [CascadeType.ALL])
    var draws: List<ApplyDraw> = mutableListOf(),

    @OneToMany(mappedBy = "apply", cascade = [CascadeType.ALL])
    var links: List<Apply_Link> = mutableListOf(),

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CONTEST_ID")
    var contest: Contest? = null,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "MEMBER_ID")
    var member: Member? = null

) : Auditable<Long>()