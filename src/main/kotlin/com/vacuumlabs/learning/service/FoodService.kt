package com.vacuumlabs.learning.service

import com.vacuumlabs.learning.food.Food
import com.vacuumlabs.learning.ingredient.Ingredient
import com.vacuumlabs.learning.repository.FoodRepository
import com.vacuumlabs.learning.repository.FoodTagRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.web.server.ResponseStatusException
import java.util.*
import kotlin.collections.HashSet

@Service
class FoodService @Autowired constructor(
    val foodRepository : FoodRepository,
    val foodTagRepository: FoodTagRepository
) {

    fun getAll() : List<Food> {
        return foodRepository.findAll()
    }

    fun isFoodFilledValidly(food: Food) {
        if(food.name.isBlank()) { throw ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY, "Food name must consist of at least one character!") }
        if(food.ingredients.isEmpty()) { throw ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY, "Food needs to have at least one ingredient!") }

        food.ingredients.forEachIndexed { index, ingredient ->  isIngredientFilledValidly(ingredient, index) }
    }

    private fun isIngredientFilledValidly(ingredient : Ingredient, index: Int) {
        if(ingredient.name.isBlank()) { throw ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY, "Ingredient at position ${index} - Name must consist of at least one character!") }
        if(ingredient.amount <= 0) { throw ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY, "Ingredient at position ${index} - Amount must be positive!") }
    }

    fun saveFood(food : Food) : Food {
        food.ingredients.forEach { it.food = food }
        loadTagsFromDbByNameAndSaveNewTagsToDb(food)
        return foodRepository.save(food)
    }

    fun loadTagsFromDbByNameAndSaveNewTagsToDb(food : Food) {
        food.tags = food.tags.mapTo(HashSet()) {
            foodTagRepository.findFoodTagByName(it.name).orElseGet({ foodTagRepository.save(it)} )
        }
    }

    fun findOne(foodId : Int) : Optional<Food> {
        return foodRepository.findById(foodId)
    }

    fun deleteFood(foodId : Int) {
        foodRepository.deleteById(foodId)
    }
}