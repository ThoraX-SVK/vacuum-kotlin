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
        assertThrows<InvalidDataException> {
            foodService.saveFood(Food())
        }
    }

    @Test()
    fun givenFoodWithNoIngredients_thenThrowException() {
        val food = Food("Test food", mutableListOf(), mutableSetOf())

        assertThrows<InvalidDataException> {
            foodService.saveFood(food)
        }
    }

    @Test()
    fun givenFoodWithNoName_thenThrowException() {
        val food = Food("", mutableListOf(Ingredient("Test", IngredientUnitType.PCS, 1, Food())), mutableSetOf())

        assertThrows<InvalidDataException> {
            foodService.saveFood(food)
        }
    }

    @Test()
    fun givenFoodWithIngredientWithNoName_thenThrowException() {
        val food = Food("Test food", mutableListOf(Ingredient("", IngredientUnitType.PCS, 1, Food())), mutableSetOf())

        assertThrows<InvalidDataException> {
            foodService.saveFood(food)
        }
    }

    @Test()
    fun givenFoodWithNonPositiveAmount_thenThrowException() {
        val food = Food("Test food", mutableListOf(Ingredient("Test", IngredientUnitType.PCS, 0, Food())), mutableSetOf())

        assertThrows<InvalidDataException> {
            foodService.saveFood(food)
        }
    }

    @Test()
    fun givenFoodWithTwoSameIngredients_thenThrowException() {
        val food = Food("Test food",
            mutableListOf(Ingredient("Test", IngredientUnitType.PCS, 1, Food()), Ingredient("Test", IngredientUnitType.PCS, 1, Food())),
            mutableSetOf()
        )

        assertThrows<InvalidDataException> {
            foodService.saveFood(food)
        }
    }

    @Test()
    fun givenFoodWithExistingTag_thenUseTagFromDbWhenSavingFood() {

        val tag = FoodTag("testTag")
        val savedTag = foodTagRepository.save(tag)

        val food = Food("Test food",
            mutableListOf(Ingredient("Test", IngredientUnitType.PCS, 1, Food())),
            mutableSetOf(FoodTag("testTag"))
        )

        val savedFood = foodService.saveFood(food)

        assert(savedTag.id == savedFood.tags.first().id)
    }

    @Test()
    fun givenFoodWithTwoSameTags_thenOnlyOneTagSavedToDb() {
        val food = Food("Test food",
            mutableListOf(Ingredient("Test", IngredientUnitType.PCS, 1, Food())),
            mutableSetOf(FoodTag("testTag"), FoodTag("testTag"))
        )

        val savedFood = foodService.saveFood(food)

        assert(savedFood.tags.size == 1)
        assert(savedFood.tags.first().name == "testTag")
    }

    @Test()
    fun givenValidFood_thenIsSavedToDb() {
        val food = Food("Test food",
            mutableListOf(Ingredient("Test", IngredientUnitType.PCS, 1, Food()), Ingredient("Test 2", IngredientUnitType.G, 200, Food())),
            mutableSetOf(FoodTag("testTag"), FoodTag("testTag 2"))
        )

        val savedFood = foodService.saveFood(food)
        val allFood = foodService.getAll()

        assert(savedFood.id == allFood.first().id)
    }
}