package com.vacuumlabs.learning.startup

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.CommandLineRunner
import org.springframework.core.Ordered
import org.springframework.stereotype.Component

@Component
abstract class OrderedCommandLineRunner : Ordered, CommandLineRunner {

    @Autowired
    private lateinit var runnerOrder : RunnerOrder

    override fun getOrder(): Int {
        return runnerOrder.getOrder(this::class.simpleName!!)
    }

}