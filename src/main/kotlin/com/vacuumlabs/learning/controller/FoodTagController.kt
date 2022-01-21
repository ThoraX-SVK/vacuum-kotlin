package com.vacuumlabs.learning.controller

import com.vacuumlabs.learning.entity.tag.FoodTag
import com.vacuumlabs.learning.service.FoodTagService
import com.vacuumlabs.learning.service.exception.InvalidDataException
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.server.ResponseStatusException

@RestController
@RequestMapping("/api/food/tag")
class FoodTagController @Autowired constructor(
    private val foodTagService: FoodTagService
) {

    @RequestMapping(value = ["/all"], method = [RequestMethod.GET], produces = [MediaType.APPLICATION_JSON_VALUE])
    fun getAllFoodTags(): List<FoodTag> {
        return foodTagService.getAll()
    }

    @RequestMapping(value = ["/add"], method = [RequestMethod.POST], produces = [MediaType.APPLICATION_JSON_VALUE])
    fun addNewFoodTag(@RequestBody foodTag : FoodTag) {
        try {
            foodTagService.save(foodTag)
        } catch (e : InvalidDataException) {
            throw ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY, e.message)
        }
    }

}