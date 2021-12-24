package com.vicon.viconbackend.features.admin.qna

import com.vicon.viconbackend.domain.qna.Qna
import com.vicon.viconbackend.domain.qna.QnaRepository
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service
import java.util.*

@Service
class AdminQnaService(
    val qnaRepository: QnaRepository
) {
    fun findAllByPageable(pageRequest: Pageable): Page<Qna> = qnaRepository.findByEnabled(pageRequest)

    fun save(qna: Qna) = qnaRepository.save(qna)

    fun delete(id: String): Boolean {
        val qna = qnaRepository.findById(id.toLong()).get()
        return try {
            qna.enabled = false
            this.save(qna)
            true
        } catch (e: Exception) {
            false
        }
    }

    fun saveAll(qnaList: List<Qna>): MutableList<Qna> = qnaRepository.saveAll(qnaList)

    fun findById(id: Long): Optional<Qna> = qnaRepository.findById(id)

}