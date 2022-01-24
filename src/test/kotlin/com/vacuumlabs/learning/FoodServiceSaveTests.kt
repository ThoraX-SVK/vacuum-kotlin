package com.vacuumlabs.learning

import com.vacuumlabs.learning.entity.food.Food
import com.vacuumlabs.learning.entity.ingredient.Ingredient
import com.vacuumlabs.learning.entity.ingredient.IngredientUnitType
import com.vacuumlabs.learning.entity.tag.FoodTag
import com.vacuumlabs.learning.repository.FoodTagRepository
import com.vacuumlabs.learning.service.FoodService
import com.vacuumlabs.learning.service.exception.InvalidDataException
import io.zonky.test.db.AutoConfigureEmbeddedDatabase
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ActiveProfiles

@ActiveProfiles("test")
@SpringBootTest
@AutoConfigureEmbeddedDatabase(refresh = AutoConfigureEmbeddedDatabase.RefreshMode.BEFORE_EACH_TEST_METHOD)
class FoodServiceSaveTests @Autowired constructor(
    private val foodService: FoodService,
    private val foodTagRepository: FoodTagRepository
) {

    @Test()
    fun givenEmptyFood_thenThrowException() {
        assertThrows<UninitializedPropertyAccessException> {
            foodService.saveFood(Food())
        }
    }

    @Test()
    fun givenFoodWithNoIngredients_thenThrowException() {
        val food = Food()
        food.name = "Test food"
        food.ingredients = mutableListOf()

        assertThrows<InvalidDataException> {
            foodService.saveFood(food)
        }
    }

    @Test()
    fun givenFoodWithNoName_thenThrowException() {
        val food = Food()
        food.name = ""

        assertThrows<InvalidDataException> {
            foodService.saveFood(food)
        }
    }

    @Test()
    fun givenFoodWithIngredientWithNoName_thenThrowException() {
        val food = Food()
        val ingredient = Ingredient()
        ingredient.name = ""
        ingredient.unit = IngredientUnitType.G
        ingredient.amount = 100
        ingredient.food = food

        food.name = "Test food"
        food.ingredients = mutableListOf(ingredient)

        assertThrows<InvalidDataException> {
            foodService.saveFood(food)
        }
    }

    @Test()
    fun givenFoodWithNonPositiveAmount_thenThrowException() {
        val food = Food()
        val ingredient = Ingredient()
        ingredient.name = "Test ingredient"
        ingredient.unit = IngredientUnitType.G
        ingredient.amount = 0
        ingredient.food = food

        food.name = "Test food"
        food.ingredients = mutableListOf(ingredient)

        assertThrows<InvalidDataException> {
            foodService.saveFood(food)
        }
    }

    @Test()
    fun givenFoodWithTwoSameIngredients_thenThrowException() {
        val food = Food()
        val ingredient = Ingredient()
        ingredient.name = "Test ingredient"
        ingredient.unit = IngredientUnitType.G
        ingredient.amount = 100
        ingredient.food = food

        val secondIngredient = Ingredient()
        secondIngredient.name = "Test ingredient"
        secondIngredient.unit = IngredientUnitType.G
        secondIngredient.amount = 200
        secondIngredient.food = food

        food.name = "Test food"
        food.ingredients = mutableListOf(ingredient, secondIngredient)

        assertThrows<InvalidDataException> {
            foodService.saveFood(food)
        }
    }

    @Test()
    fun givenFoodWithExistingTag_thenUseTagFromDbWhenSavingFood() {

        val tag = FoodTag()
        tag.name = "testTag"
        val savedTag = foodTagRepository.save(tag)

        val food = Food()
        val ingredient = Ingredient()
        ingredient.name = "Test ingredient"
        ingredient.unit = IngredientUnitType.G
        ingredient.amount = 100
        ingredient.food = food


        food.name = "Test food"
        food.ingredients = mutableListOf(ingredient)

        val anotherTag = FoodTag()
        anotherTag.name = "testTag"
        food.tags = mutableSetOf(anotherTag)

        val savedFood = foodService.saveFood(food)

        assert(savedTag == savedFood.tags.first())
    }

    @Test()
    fun givenFoodWithTwoSameTags_thenOnlyOneTagSavedToDb() {
        val food = Food()
        val ingredient = Ingredient()
        ingredient.name = "Test ingredient"
        ingredient.unit = IngredientUnitType.G
        ingredient.amount = 100
        ingredient.food = food


        food.name = "Test food"
        food.ingredients = mutableListOf(ingredient)

        val tag = FoodTag()
        tag.name = "testTag"
        val anotherTag = FoodTag()
        anotherTag.name = "testTag"
        food.tags = mutableSetOf(tag, anotherTag)

        val savedFood = foodService.saveFood(food)

        assert(savedFood.tags.size == 1)
        assert(savedFood.tags.first().name == "testTag")
    }

    @Test()
    fun givenValidFood_thenIsSavedToDb() {
        val food = Food()
        val ingredient = Ingredient()
        ingredient.name = "Test ingredient"
        ingredient.unit = IngredientUnitType.G
        ingredient.amount = 100
        ingredient.food = food

        food.name = "Test food"
        food.ingredients = mutableListOf(ingredient)
        val tag = FoodTag()
        tag.name = "testTag"
        val anotherTag = FoodTag()
        anotherTag.name = "testTag 2"

        food.tags = mutableSetOf(tag, anotherTag)

        val savedFood = foodService.saveFood(food)
        val allFood = foodService.getAll()

        assert(savedFood == allFood.first())
    }
}