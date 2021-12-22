package com.vicon.viconbackend

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.springframework.util.Base64Utils

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

    @Test
    fun `Base64 와 Base64Utils 의 차이 확인`() {
        val key = "1"

//        val a = Base64.getEncoder().encode(key.toByteArray()).toString()
//        val b = Base64.getDecoder().decode(a).toString()

        val c = Base64Utils.encodeToString(key.toByteArray())
        val d = key.encodeToByteArray()
        val d2 = key.encodeToByteArray().toString()
        val e = Base64Utils.decodeFromString(c)
        val f = d.decodeToString()
        val f2 = d2.toByteArray().decodeToString()

//        println(a)
//        println(b)
        println(c)
        println(d)
        println(d2)
        println(e)
        println(f)
        println(f2)

    }

    @Test
    fun `a`() {
        fun getBase64EncodeString(content: String): String {
            return Base64Utils.encodeToString(content.toByteArray()) //TODO Base64 암호화된 문자열로 반환
        }

        fun getBase64EncodeByte(content: ByteArray?): String {
            return Base64Utils.encodeToString(content!!) //TODO Base64 암호화된 문자열로 반환
        }

        fun getBase64DecodeString(content: String): String {
            return String(Base64Utils.decode(content.toByteArray())) //TODO Base64 복호화된 문자열값 반환
        }

        fun getBase64DecodeByte(content: ByteArray?): String {
            return String(Base64Utils.decode(content!!)) //TODO Base64 복호화된 문자열값 반환
        }

        // 초기 문자열 및 byte 값 변수 선언 실시
        val key = "test1"

        //base64 인코딩 수행 실시 [인풋 : 문자열 >> 아웃풋 : base64 인코딩 문자열]
        val strBase64Encode: String = getBase64EncodeString(key)

        //base64 인코딩 수행 실시 [인풋 : 바이트 >> 아웃풋 : base64 인코딩 문자열]
        val bytBase64Encode: String = getBase64EncodeByte(key.toByteArray())

        //base64 디코딩 수행 실시 [인풋 : base64 문자열 >> 아웃풋 : 디코딩 문자열]
        val strBase64Decode: String = getBase64DecodeString(strBase64Encode)

        //base64 디코딩 수행 실시 [인풋 : base64 바이트 >> 아웃풋 : 디코딩 문자열]
        val bytBase64Decode: String = getBase64DecodeByte(bytBase64Encode.toByteArray())

        println("original : $key")
        println(strBase64Encode)
        println(bytBase64Encode)
        println(strBase64Decode)
        println(bytBase64Decode)
    }

    @Test
    fun `enum class의 반환값 확인`() {
        val value = TestEnum.FIRST.value
        val name = TestEnum.FIRST.name
        val ordinal = TestEnum.FIRST.ordinal

        println(value) //첫번째
        println(name) // FIRST
        println(ordinal) // 0

    }

}

enum class TestEnum(val value: String) {
    FIRST("첫번째"),
    SECOND("두번째"),
    THIRD("세번째")
}