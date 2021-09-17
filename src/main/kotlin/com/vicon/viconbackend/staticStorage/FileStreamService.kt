package com.vicon.viconbackend.staticStorage

import org.springframework.stereotype.Service
import java.io.File
import java.io.FileOutputStream
import java.util.*

@Service
class FileStreamService(
        private val imagePathConvertService: ImagePathConvertService
) {

//    fun writeHtml(content: String): File {
//        return createFile()
//                .apply {
//                    FileOutputStream(this).run {
//                        write(contentToByteArray(content))
//                        close()
//                    }
//                }
//    }
//
//    private fun contentToByteArray(content: String) =
//            imagePathConvertService.convertForHtml("<div>$content</div>").toByteArray()

    private fun createFile() = File("${UUID.randomUUID()}.html")

}
