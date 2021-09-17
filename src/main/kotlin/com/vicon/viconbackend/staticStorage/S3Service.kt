package com.vicon.viconbackend.staticStorage

import com.amazonaws.services.s3.AmazonS3
import com.amazonaws.services.s3.model.DeleteObjectRequest
import com.amazonaws.services.s3.model.GetObjectRequest
import com.amazonaws.services.s3.model.PutObjectRequest
import com.amazonaws.services.s3.transfer.TransferManager
import com.vicon.viconbackend.staticStorage.WordProvider.Companion.DELIMITER
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service
import java.io.File
import java.io.FileInputStream
import java.util.*

@Service
class S3Service(
        val transferManager: TransferManager,
        val amazonS3: AmazonS3,
        val s3Configuration: S3Configuration,
        val wordProvider: WordProvider
) {

    private val logger = LoggerFactory.getLogger(S3Service::class.java)

    fun get(directory: String, fileName: String): ByteArray {
        return try {
            val file = File(fileName)
            val request = GetObjectRequest(s3Configuration.bucketName, directory + DELIMITER + file.name)
            transferManager.download(request, file).waitForCompletion()

            return fileToByteArray(file)
                    .apply {
                        Optional.of(file.exists()).ifPresent { if (it) file.delete() }
                    }
        } catch (e: Exception) {
            logger.info(e.message)
            ByteArray(0)
        }
    }

    fun save(directory: String, uploadFile: File): String {
        val key = wordProvider.createFileName(directory, uploadFile)
        val putRequest = PutObjectRequest(s3Configuration.bucketName, key, uploadFile)
        amazonS3.putObject(putRequest)
        val putFilePath = amazonS3.getUrl(s3Configuration.bucketName, key).toString()
        Optional.of(uploadFile.exists()).ifPresent { if (it) uploadFile.delete() }

        return wordProvider.toCDNPath(putFilePath)
    }

    fun delete(directory: String, fileName: String) {
        amazonS3.deleteObject(DeleteObjectRequest(s3Configuration.bucketName, directory + DELIMITER + fileName))
    }

    private fun fileToByteArray(file: File): ByteArray {
        return FileInputStream(file).use {
            val byteArray = ByteArray(file.length().toInt())
            it.read(byteArray)
            byteArray
        }
    }
}