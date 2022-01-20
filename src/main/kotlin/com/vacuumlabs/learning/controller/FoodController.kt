package com.vacuumlabs.learning.controller

import com.vacuumlabs.learning.food.Food
import com.vacuumlabs.learning.repository.FoodRepository
import com.vacuumlabs.learning.service.FoodService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.server.ResponseStatusException

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
    fun addNewFood(@RequestBody food: Food): ResponseEntity<Food> {
        foodService.isFoodFilledValidly(food)
        val savedFood = foodService.saveFood(food);
        return ResponseEntity.ok(savedFood)
    }

    @RequestMapping(value = ["/edit/{foodId}"], method = [RequestMethod.PUT], produces = [MediaType.APPLICATION_JSON_VALUE])
    fun editFood(@PathVariable foodId : Int, @RequestBody food : Food) {
        foodService.isFoodFilledValidly(food)
        val persistedFood = foodService.findOne(foodId).orElseThrow { throw ResponseStatusException(HttpStatus.NOT_FOUND, "No food with given ID.") }
        food.id = persistedFood.id

        foodService.saveFood(food)
    }

    @RequestMapping(value = ["/delete/{foodId}"], method = [RequestMethod.DELETE])
    fun deleteFood(@PathVariable foodId : Int) : ResponseEntity<Int> {
        foodService.deleteFood(foodId)
        return ResponseEntity.ok().body(foodId);
    }


}