package com.vicon.viconbackend.features.board

import com.vicon.viconbackend.domain.board.Board

data class BoardDTO(
    val link: String? = "",
    val title: String = "",
    val createDate: String = ""
) {
    companion object {
        fun of(board: Board): BoardDTO {
            return BoardDTO(
                link = board.link,
                title = board.title!!,
                createDate = board.createdAt!!.toLocalDate().toString()
            )
        }
    }
}