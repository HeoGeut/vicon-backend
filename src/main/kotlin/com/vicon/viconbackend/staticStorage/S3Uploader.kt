package com.vicon.viconbackend.staticStorage

import com.amazonaws.services.s3.AmazonS3
import com.amazonaws.services.s3.model.CannedAccessControlList
import com.amazonaws.services.s3.model.ObjectMetadata
import com.amazonaws.services.s3.model.PutObjectRequest
import org.springframework.stereotype.Component
import org.springframework.web.multipart.MultipartFile
import java.util.*

@Component
class S3Uploader(
    val amazonS3Client: AmazonS3,
    val s3Config: S3Config
) {

    fun upload(file: MultipartFile, directoryPath: String): String {
        val fileName = convertFileName(directoryPath, file)
        val metaData = getMetaData(file)
        amazonS3Client.putObject(getPutObjectRequest(fileName, file, metaData))

        return amazonS3Client.getUrl(s3Config.bucketName, fileName).toString()
    }

    private fun getPutObjectRequest(
        fileName: String,
        file: MultipartFile,
        metaData: ObjectMetadata
    ) = PutObjectRequest(s3Config.bucketName, fileName, file.inputStream, metaData)
        .withCannedAcl(CannedAccessControlList.PublicRead)

    private fun getMetaData(file: MultipartFile): ObjectMetadata {
        return ObjectMetadata().apply {
            this.contentLength = file.size
            this.contentType = file.contentType
        }
    }

    private fun convertFileName(dirName: String, file: MultipartFile) =
        dirName + "/" + createFileName(file.originalFilename!!)

    private fun createFileName(name: String): String {
        return UUID.randomUUID().toString() + getFileExtension(name)
    }

    private fun getFileExtension(name: String): String {
        return name.substring(name.lastIndexOf("."))
    }
}