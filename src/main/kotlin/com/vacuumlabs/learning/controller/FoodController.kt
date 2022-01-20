package com.vacuumlabs.learning.controller

import com.vacuumlabs.learning.food.Food
import com.vacuumlabs.learning.repository.FoodRepository
import com.vacuumlabs.learning.service.FoodService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/food")
class FoodController @Autowired constructor(
    val foodService: FoodService,
) {

    @RequestMapping(value = ["/all"], method = [RequestMethod.GET], produces = [MediaType.APPLICATION_JSON_VALUE])
    fun getAllFood(): List<Food> {
        return foodService.getAll()
    }

    @RequestMapping(value = ["/add"], method = [RequestMethod.POST], produces = [MediaType.APPLICATION_JSON_VALUE])
    fun addNewFood(@RequestBody food: Food): ResponseEntity<*> {
        foodService.isFoodFilledValidly(food)
        val savedFood = foodService.saveFood(food);
        return ResponseEntity.ok(savedFood)
    }

}