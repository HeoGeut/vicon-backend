package com.vicon.viconbackend.domain.apply

import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface ApplyRepository : JpaRepository<Apply, Long> {
    override fun findById(id: Long) : Optional<Apply>
}