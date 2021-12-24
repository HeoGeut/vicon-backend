package com.vicon.viconbackend.domain.faq

import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository

interface FaqRepository : JpaRepository<Faq, Long> {
    fun findByEnabled(pageable: Pageable, enabled: Boolean = true) : Page<Faq>
}