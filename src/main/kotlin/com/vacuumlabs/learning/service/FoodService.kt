package com.vacuumlabs.learning.service

import com.vacuumlabs.learning.entity.food.Food
import com.vacuumlabs.learning.repository.FoodRepository
import com.vacuumlabs.learning.service.exception.InvalidDataException
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.*
import kotlin.collections.HashSet

@Service
class FoodService @Autowired constructor(
    val foodRepository : FoodRepository,
    val ingredientService: IngredientService,
    val foodTagService: FoodTagService
) {

    fun getAll() : List<Food> {
        return foodRepository.findAll()
    }

    fun throwIfFoodNotValid(food: Food) {
        if(food.name.isBlank()) { throw InvalidDataException("Food name must consist of at least one character!") }
        if(food.ingredients.isEmpty()) { throw InvalidDataException("Food needs to have at least one ingredient!") }

        food.ingredients.forEachIndexed { index, ingredient ->  ingredientService.throwIfIngredientNotValid(ingredient, index) }
    }

    @Transactional(rollbackFor = [Exception::class])
    fun saveFood(food : Food) : Food {
        throwIfFoodNotValid(food)
        loadTagsFromDbByNameAndSaveNewTagsToDb(food)
        food.ingredients.forEach { it.food = food }
        return foodRepository.save(food)
    }

    fun loadTagsFromDbByNameAndSaveNewTagsToDb(food : Food) {
        food.tags = food.tags.mapTo(HashSet()) {
            foodTagService.findByName(it.name).orElseGet { foodTagService.save(it) }
        }
    }

    fun findOne(foodId : Int) : Optional<Food> {
        return foodRepository.findById(foodId)
    }

    fun deleteFood(foodId : Int) {
        foodRepository.deleteById(foodId)
    }
}