package com.vicon.viconbackend.features.contest.service

import com.vicon.viconbackend.domain.contest.*
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.annotation.Rollback
import org.springframework.test.context.TestConstructor
import java.math.BigDecimal
import java.time.LocalDateTime

@SpringBootTest
@TestConstructor(autowireMode = TestConstructor.AutowireMode.ALL)
@Rollback(false)
class ContestServiceTest(
    val contestRepository: ContestRepository
) {

}