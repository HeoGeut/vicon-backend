package com.vicon.viconbackend.domain.board

import com.vicon.viconbackend.domain.common.Auditable
import javax.persistence.Column
import javax.persistence.Entity

@Entity
data class Board(
    var title: String? = "",

    @Column(name = "content", columnDefinition = "text")
    var content: String? = "",

    var img: String? = "",
    var link: String? = ""

) : Auditable<Long>()