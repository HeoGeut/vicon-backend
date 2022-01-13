package com.vicon.viconbackend.features.apply

import com.vicon.viconbackend.domain.apply.Apply
import com.vicon.viconbackend.domain.apply.ApplyRepository
import com.vicon.viconbackend.domain.contest.ContestRepository
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service

@Service
class ApplyService(
    val applyRepository: ApplyRepository,
    val contestRepository: ContestRepository
) {

    private val logger = LoggerFactory.getLogger(this.javaClass)

    fun findById(id: Long) = applyRepository.findById(id)
    fun save(apply: Apply) = applyRepository.save(apply)

    fun switchConfirm(apply: Apply): Int {
        val findContest = contestRepository.findById(apply.contest!!.id!!).get()
        val recruit = findContest.recruitNumber.toInt()
        val recruited = findContest.applies!!.filter { it.isConfirm == true }.size
        return if (recruit > recruited) {
            apply.isConfirm = !apply.isConfirm!!
            this.save(apply)
            1
        } else if (apply.isConfirm == true) {
            apply.isConfirm = false
            this.save(apply)
            1
        } else {
            0
        }
    }
}