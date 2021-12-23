package com.vicon.viconbackend.features.admin.event

import com.vicon.viconbackend.domain.event.EventRepository
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service

//@Service
class AdminEventService(
    val eventRepository: EventRepository
) {
    fun findAllByPageable(pageRequest : Pageable) = eventRepository.findByEnabled(pageRequest)
}