package com.vicon.viconbackend.features.contest

import org.springframework.util.Base64Utils

object test {
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
}