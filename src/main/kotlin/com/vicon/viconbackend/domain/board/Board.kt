package com.vicon.viconbackend.domain.board

import com.vicon.viconbackend.domain.common.Auditable
import javax.persistence.Entity

@Entity
data class Board(
    var tile: String? = "",
    var content: String? = "",
    var img: String? = "",
    var link: String? = ""

) : Auditable<Long>()