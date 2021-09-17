package com.vicon.viconbackend.features.contest.api

import com.vicon.viconbackend.features.contest.ContestService
import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.TestConstructor

@SpringBootTest
@TestConstructor(autowireMode = TestConstructor.AutowireMode.ALL)
class ContestControllerTest(
    val contentService: ContestService
) {
    @Test
    fun `콘테스트를 저장할 수 있다`() {


    }

}