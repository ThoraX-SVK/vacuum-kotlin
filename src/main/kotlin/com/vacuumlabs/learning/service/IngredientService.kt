package com.vacuumlabs.learning.service

import com.vacuumlabs.learning.entity.ingredient.Ingredient
import com.vacuumlabs.learning.service.exception.InvalidDataException
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.web.server.ResponseStatusException

@Service
class IngredientService {

    fun throwIfIngredientNotValid(ingredient : Ingredient, index: Int) {
        if(ingredient.name.isBlank()) { throw InvalidDataException("Ingredient at position ${index} - Name must consist of at least one character!") }
        if(ingredient.amount <= 0) { throw InvalidDataException("Ingredient at position ${index} - Amount must be positive!") }
    }

}