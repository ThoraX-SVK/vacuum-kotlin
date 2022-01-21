package com.vacuumlabs.learning.service

import com.vacuumlabs.learning.entity.tag.FoodTag
import com.vacuumlabs.learning.repository.FoodTagRepository
import com.vacuumlabs.learning.service.exception.InvalidDataException
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import kotlin.jvm.Throws

@Service
class FoodTagService @Autowired constructor(
    private val foodTagRepository: FoodTagRepository,
) {

    fun getAll() : List<FoodTag> {
        return foodTagRepository.findAll()
    }


    fun throwExceptionIfFoodTagNotValid(foodTag: FoodTag) : Boolean {
        if(foodTag.name.isBlank()) {
           throw InvalidDataException("Name must consist of at least one character!")
        }

        return true
    }

    fun save(foodTag : FoodTag) : FoodTag {
        throwExceptionIfFoodTagNotValid(foodTag)
        return foodTagRepository.save(foodTag)
    }


}