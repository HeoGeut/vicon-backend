package com.vicon.viconbackend.features.apply

import com.vicon.viconbackend.domain.apply.ApplyRepository
import org.springframework.stereotype.Service

@Service
class ApplyService(
    val applyRepository: ApplyRepository
) {
    fun findById(id: Long) = applyRepository.findById(id)
}