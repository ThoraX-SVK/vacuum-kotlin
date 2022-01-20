package com.vacuumlabs.learning.controller

import com.vacuumlabs.learning.food.Food
import com.vacuumlabs.learning.repository.FoodRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.MediaType
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/food")
class FoodController @Autowired constructor(
    val foodRepository : FoodRepository
) {

    @RequestMapping(value = ["/all"], method = [RequestMethod.GET], produces = [MediaType.APPLICATION_JSON_VALUE])
    fun getAllFood() : List<Food> {
        return foodRepository.findAll()
    }
}