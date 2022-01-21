package com.vacuumlabs.learning.startup.runners

import com.vacuumlabs.learning.entity.food.Food
import com.vacuumlabs.learning.entity.tag.FoodTag
import com.vacuumlabs.learning.entity.ingredient.Ingredient
import com.vacuumlabs.learning.entity.ingredient.IngredientUnitType
import com.vacuumlabs.learning.repository.FoodRepository
import com.vacuumlabs.learning.repository.FoodTagRepository
import com.vacuumlabs.learning.startup.OrderedCommandLineRunner
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
class DatabasePopulationRunner @Autowired constructor(
    val foodRepository : FoodRepository,
    val foodTagRepository: FoodTagRepository
) : OrderedCommandLineRunner() {

    override fun run(vararg args: String?) {

        val tag = FoodTag("Breakfast")
        foodTagRepository.save(tag)

        val food : Food = Food("toast", mutableListOf(), mutableSetOf(tag))
        val toastBread = Ingredient("Toast bread bun", IngredientUnitType.PCS, 1, food)
        val butter = Ingredient("Butter", IngredientUnitType.PCS, 1, food)
        val ham = Ingredient("Ham", IngredientUnitType.PCS, 1, food)
        val cheese = Ingredient("Cheddar cheese", IngredientUnitType.PCS, 1, food)

        food.ingredients.add(toastBread)
        food.ingredients.add(butter)
        food.ingredients.add(ham)
        food.ingredients.add(cheese)

        foodRepository.save(food)
    }
}