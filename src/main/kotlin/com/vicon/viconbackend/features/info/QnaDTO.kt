package com.vicon.viconbackend.features.info

import com.vicon.viconbackend.domain.qna.Qna

data class QnaDTO(
    val title: String = "",
    val user: String = "",
    val writeDate: String = "",
    val status: String = ""
) {
    companion object {
        fun of(qna: Qna): QnaDTO {
            return QnaDTO(
                title = qna.title.toString(),
                user = qna.member!!.username.toString(),
                writeDate = qna.createdAt!!.toLocalDate().toString(),
                status = getStatus(qna.isConfirmed!!)
            )
        }

        private fun getStatus(isConfirmed: Boolean): String {
            return if (isConfirmed) {
                "답변완료"
            } else {
                "답변대기"
            }
        }
    }
}