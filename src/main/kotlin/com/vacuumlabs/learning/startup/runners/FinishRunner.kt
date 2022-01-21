package com.vacuumlabs.learning.startup.runners

import com.vacuumlabs.learning.startup.OrderedCommandLineRunner
import org.springframework.context.annotation.Profile
import org.springframework.stereotype.Component


@Component
@Profile("!test")
class FinishRunner : OrderedCommandLineRunner() {

    override fun run(vararg args: String?) {
        println(" - - - - - - -")
        println("|  App ready! |")
        println(" - - - - - - -")
    }

}