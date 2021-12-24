package com.vicon.viconbackend.features.admin.faq

import com.vicon.viconbackend.domain.faq.Faq
import com.vicon.viconbackend.domain.faq.FaqRepository
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service

@Service
class AdminFaqService(
    val faqRepository: FaqRepository
) {
    fun findById(id: Long) = faqRepository.findById(id)

    fun save(faq: Faq) = faqRepository.save(faq)

    fun saveAll(faqList: List<Faq>): MutableList<Faq> = faqRepository.saveAll(faqList)

    fun delete(id: String): Boolean {
        val faq = faqRepository.findById(id.toLong()).get()
        return try {
            faq.enabled = false
            this.save(faq)
            true
        } catch (e: Exception) {
            false
        }
    }

    fun findAllByPageable(pageRequest: Pageable): Page<Faq> = faqRepository.findByEnabled(pageRequest)

}