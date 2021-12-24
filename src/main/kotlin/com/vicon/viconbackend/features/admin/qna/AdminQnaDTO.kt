package com.vicon.viconbackend.features.admin.qna

import com.vicon.viconbackend.domain.qna.Qna
import java.time.format.DateTimeFormatter

data class AdminQnaDTO(
    val id: String = "",
    val title: String = "",
    val username: String = "",
    val content: String = "",
    val isConfirmed: Boolean = false,
    val createdDate: String = "",
    val answer: String = ""
) {
    companion object {
        fun of(qna: Qna): AdminQnaDTO {
            return AdminQnaDTO(
                id = qna.id.toString(),
                title = qna.title!!,
                username = qna.member!!.username,
                content = qna.content!!,
                isConfirmed = qna.isConfirmed!!,
                createdDate = qna.createdAt!!.format(DateTimeFormatter.ofPattern("yy-MM-dd HH:mm")),
                answer = qna.answer!!
            )
        }
    }
}