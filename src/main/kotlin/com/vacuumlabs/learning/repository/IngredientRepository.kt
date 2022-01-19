package com.vacuumlabs.learning.repository

import com.vacuumlabs.learning.ingredient.Ingredient
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface IngredientRepository : JpaRepository<Ingredient, Int> {

}