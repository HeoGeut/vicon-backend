package com.vicon.viconbackend

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class KotlinTest {

    @Test
    fun `when문에서 람다를 반환해도 정상적인 결과가 나오는지 확인`() {
        //given
        val a = 1
        val b = 2

        //when
        val c = when (a) {
            1 -> {
                val d = 1
                val e = 2
                val f = d + e
                b + 1
            }
            2 -> 10
            3 -> 11
            else -> 12
        }

        println(c)

        //then
        Assertions.assertEquals(3, c)
    }
}