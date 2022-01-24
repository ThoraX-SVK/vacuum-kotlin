package com.vacuumlabs.learning.startup.runners

import com.vacuumlabs.learning.entity.food.Food
import com.vacuumlabs.learning.entity.tag.FoodTag
import com.vacuumlabs.learning.entity.ingredient.Ingredient
import com.vacuumlabs.learning.entity.ingredient.IngredientUnitType
import com.vacuumlabs.learning.repository.FoodRepository
import com.vacuumlabs.learning.repository.FoodTagRepository
import com.vacuumlabs.learning.startup.OrderedCommandLineRunner
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Profile
import org.springframework.stereotype.Component

@Component
@Profile("!test")
class DatabasePopulationRunner @Autowired constructor(
    val foodRepository : FoodRepository,
    val foodTagRepository: FoodTagRepository
) : OrderedCommandLineRunner() {

    override fun run(vararg args: String?) {

    }
}