package com.vacuumlabs.learning.startup

import com.vacuumlabs.learning.startup.runners.FinishRunner
import org.springframework.stereotype.Component

@Component
class RunnerOrder {

    val order : List<String> = arrayListOf(
        FinishRunner::class.simpleName!!
    )

    fun getOrder(runnerName: String) : Int {
        return order.indexOf(runnerName)
    }
}