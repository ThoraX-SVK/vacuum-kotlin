package com.vacuumlabs.learning.startup.runners

import com.vacuumlabs.learning.startup.OrderedCommandLineRunner
import org.springframework.stereotype.Component


@Component
class FinishRunner : OrderedCommandLineRunner() {

    override fun run(vararg args: String?) {
        println(" - - - - - - -")
        println("|  App ready! |")
        println(" - - - - - - -")
    }

}