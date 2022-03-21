package com.vicon.viconbackend.domain.payment

import org.springframework.data.repository.CrudRepository

interface PaymentRepository : CrudRepository<Payment, Long> {
}