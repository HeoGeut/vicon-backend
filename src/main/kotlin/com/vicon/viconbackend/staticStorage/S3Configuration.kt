package com.vicon.viconbackend.staticStorage

import com.amazonaws.AmazonClientException
import com.amazonaws.auth.AWSStaticCredentialsProvider
import com.amazonaws.auth.BasicAWSCredentials
import com.amazonaws.regions.Regions
import com.amazonaws.services.s3.AmazonS3
import com.amazonaws.services.s3.AmazonS3ClientBuilder
import com.amazonaws.services.s3.internal.Constants
import com.amazonaws.services.s3.transfer.TransferManager
import com.amazonaws.services.s3.transfer.TransferManagerBuilder
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import java.lang.Long
import java.util.*
import java.util.concurrent.Executors
import java.util.concurrent.ThreadFactory
import java.util.concurrent.ThreadPoolExecutor
import javax.management.timer.Timer

@Configuration
class S3Configuration {
    private val logger = LoggerFactory.getLogger(S3Configuration::class.java)

    @Value("\${cloud.aws.credentials.accessKey}")
    lateinit var accessKey: String

    @Value("\${cloud.aws.credentials.secretKey}")
    lateinit var secretKey: String

    @Value("\${cloud.aws.region.static}")
    lateinit var region: String

    @Value("\${cloud.aws.s3.bucket}")
    lateinit var bucketName: String

    @Bean
    fun s3Client(): AmazonS3 {
        return AmazonS3ClientBuilder.standard()
            .withRegion(Regions.fromName(region))
            .withCredentials(AWSStaticCredentialsProvider(BasicAWSCredentials(accessKey, secretKey)))
            .build()
    }

    @Bean
    fun transferManager(): TransferManager {
        val transferManager = TransferManagerBuilder.standard()
            .withS3Client(s3Client())
            .withDisableParallelDownloads(false)
            .withMinimumUploadPartSize(Long.valueOf((0 * Constants.MB).toLong()))
            .withMultipartUploadThreshold(Long.valueOf((16 * Constants.MB).toLong()))
            .withMultipartCopyPartSize(Long.valueOf((5 * Constants.MB).toLong()))
            .withMultipartCopyThreshold(Long.valueOf((100 * Constants.MB).toLong()))
            .withExecutorFactory { createExecutorService(20) }
            .build()

        val oneDayAgo = Date(System.currentTimeMillis() - Timer.ONE_DAY)

        try {
            transferManager.abortMultipartUploads(bucketName, oneDayAgo)
        } catch (e: AmazonClientException) {
            logger.error("Unable to upload file, upload was aborted, reason: ${e.message}")
        }

        return transferManager
    }

    private fun createExecutorService(threadNumber: Int): ThreadPoolExecutor {
        val threadFactory = object : ThreadFactory {
            private var threadCount = 1

            override fun newThread(r: Runnable): Thread {
                val thread = Thread(r)
                thread.name = "jsa-amazon-s3-transfer-manager-worker-" + threadCount++
                return thread
            }
        }
        return Executors.newFixedThreadPool(threadNumber, threadFactory) as ThreadPoolExecutor
    }
}