package com.riti.backendforfrontend;


import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.data.r2dbc.repository.config.EnableR2dbcRepositories
import org.springframework.scheduling.annotation.EnableAsync
import org.springframework.scheduling.annotation.EnableScheduling

@EnableR2dbcRepositories
@EnableScheduling
@EnableAsync
@SpringBootApplication
class DatingBeApplication

fun main(args: Array<String>) {
    runApplication<com.riti.backendforfrontend.DatingBeApplication>(*args)
}
