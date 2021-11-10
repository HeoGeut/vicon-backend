package com.vicon.viconbackend.domain.review

import org.springframework.data.jpa.repository.JpaRepository

interface ReviewRepository : JpaRepository<Review, Long> {
    fun findTop5By(): List<Review>
    fun findTop3By(): List<Review>
}