package com.vicon.viconbackend.staticStorage

import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component
import java.io.File
import java.net.URL
import java.util.concurrent.ThreadLocalRandom
import kotlin.streams.asSequence


@Component
class WordProvider {

    @Value("\${cdn.path}")
    private lateinit var cdnPath: String


    fun createFileName(directory: String, uploadFile: File) =
            directory + DELIMITER + randomWord() + EXTENSION + uploadFile.extension

    fun randomWord(size: Long = 24): String {
        return ThreadLocalRandom.current()
                .ints(size, 0, charPool.size)
                .asSequence()
                .map(charPool::get)
                .joinToString("")
    }


    fun toCDNPath(value: String): String {
        return if (value.isEmpty()) "" else "$cdnPath${URL(value).path}"
    }

    companion object {
        val charPool: List<Char> = ('a'..'z') + ('A'..'Z') + ('0'..'9')
        const val DELIMITER = "/"
        const val EXTENSION = "."
    }
}