package com.vacuumlabs.learning.controller

import com.vacuumlabs.learning.entity.food.Food
import com.vacuumlabs.learning.service.FoodService
import com.vacuumlabs.learning.service.exception.InvalidDataException
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.dao.EmptyResultDataAccessException
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

    @RequestMapping(value = ["/add"], method = [RequestMethod.POST], produces = [MediaType.APPLICATION_JSON_VALUE])
    fun addNewFood(@RequestBody food: Food): ResponseEntity<Food> {
        try {
            val savedFood = foodService.saveFood(food);
            return ResponseEntity.ok(savedFood)
        } catch (e : InvalidDataException) {
            throw ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY, e.message)
        }
    }

    @RequestMapping(value = ["/all"], method = [RequestMethod.GET], produces = [MediaType.APPLICATION_JSON_VALUE])
    fun getAllFood(): List<Food> {
        return foodService.getAll()
    }

    @RequestMapping(value = ["/{foodId}"], method = [RequestMethod.GET], produces = [MediaType.APPLICATION_JSON_VALUE])
    fun getFood(@PathVariable foodId : Int) : ResponseEntity<Food> {
        val foundFood = foodService.findOne(foodId)
                                    .orElseThrow { throw ResponseStatusException(HttpStatus.NOT_FOUND, "No food with given ID.") }

        return ResponseEntity.ok(foundFood)
    }

    @RequestMapping(value = ["/edit/{foodId}"], method = [RequestMethod.PUT], produces = [MediaType.APPLICATION_JSON_VALUE])
    fun editFood(@PathVariable foodId : Int, @RequestBody food : Food) : ResponseEntity<Food> {
        val persistedFood = foodService.findOne(foodId)
                                        .orElseThrow { throw ResponseStatusException(HttpStatus.NOT_FOUND, "No food with given ID.") }

        food.id = persistedFood.id

        try {
            val savedFood = foodService.saveFood(food);
            return ResponseEntity.ok(savedFood)
        } catch (e : InvalidDataException) {
            throw ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY, e.message)
        }
    }

    @RequestMapping(value = ["/delete/{foodId}"], method = [RequestMethod.DELETE])
    fun deleteFood(@PathVariable foodId : Int) : ResponseEntity<Int> {
        try {
            foodService.deleteFood(foodId)
        } catch(_: EmptyResultDataAccessException) { /* No need to do anything, tell user that resource is gone. */ }

        return ResponseEntity.ok(foodId)
    }
}