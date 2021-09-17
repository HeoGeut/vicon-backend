package com.vicon.viconbackend.staticStorage

import org.apache.commons.io.FileUtils.copyURLToFile
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
import java.io.File
import java.net.URL
import java.util.*

@Service
class ImagePathConvertService(
    val s3Service: S3Service
) {
    private val logger = LoggerFactory.getLogger(this.javaClass.simpleName)

//    @Value("\${image.home.url}")
//    private lateinit var path: String

//    fun convertForHtml(html: String): String {
//        var convertedHtml = html
//
//        Jsoup.parse(html)
//            .select("img")
//            .map { it.attr("src") }
//            .forEach {
//                val fileByUrl = getFileByUrl(it) ?: return@forEach
//                val s3Url = s3Service.save(IMAGE_DIRECTORY, fileByUrl)
//                convertedHtml = convertedHtml.replace(it, s3Url)
//            }
//
//        return convertedHtml
//    }

    fun getFileByUrl(url: String): File? {
        val file = File("${UUID.randomUUID()}.${getExt(url)}")

        try {
            copyURLToFile(generateURL(url), file)
        } catch (ex: Exception) {
            logger.error(ex.message)
            logger.error("error url : $url")
            return null
        }

        return file
    }

    private fun getExt(url: String) = url.substringAfterLast(".")

    private fun generateURL(url: String) = URL(getUrl(url))

    private fun getUrl(url: String): String {
        return when {
            url.startsWith("http") -> url
            url.startsWith("//") -> "http:$url"
//            url.startsWith("/") -> path + url.substringAfter("/")
//            else -> path + url
            else -> url
        }
    }

    companion object {
        private const val IMAGE_DIRECTORY = "image"
    }
}
