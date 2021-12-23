package com.vicon.viconbackend.domain.event

import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository

interface EventRepository : JpaRepository<Event, Long> {
    fun findByEnabled(pageable: Pageable, enabled: Boolean = true) : Page<Event>
}