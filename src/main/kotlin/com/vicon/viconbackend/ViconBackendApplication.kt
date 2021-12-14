package com.vicon.viconbackend

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import javax.annotation.PostConstruct

@SpringBootApplication
class ViconBackendApplication{

}

fun main(args: Array<String>) {
    runApplication<ViconBackendApplication>(*args)
}
