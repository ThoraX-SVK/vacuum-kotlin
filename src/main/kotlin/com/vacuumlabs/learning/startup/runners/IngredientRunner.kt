package com.vacuumlabs.learning.startup.runners

import com.vacuumlabs.learning.ingredient.Ingredient
import com.vacuumlabs.learning.ingredient.IngredientCountType
import com.vacuumlabs.learning.repository.IngredientRepository
import com.vacuumlabs.learning.startup.OrderedCommandLineRunner
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
class IngredientRunner @Autowired constructor(
    val ingredientRepository : IngredientRepository
) : OrderedCommandLineRunner() {

    override fun run(vararg args: String?) {
        val ingredients = List(5) { Ingredient("ingrediencia${it}", IngredientCountType.WEIGHT) }
        ingredientRepository.saveAll(ingredients)
    }
}