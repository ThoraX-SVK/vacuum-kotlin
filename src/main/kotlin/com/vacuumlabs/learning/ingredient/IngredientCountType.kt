package com.vacuumlabs.learning.ingredient

/**
 *
 * BOOLEAN - Something you either have or don`t have and is used in very small amounts (e.g. salt, pepper, olive oil)
 * AMOUNT - An exact amount of items is needed (e.g. 3 eggs, 2 onions)
 * VOLUME - An volume of units is needed (e.g. 500ml of milk, 1l of wine)
 * WEIGHT - An exact weight is needed (e.g. 300g of flour, 500g of chicken meat)
 *
 */
enum class IngredientCountType {
    BOOLEAN,
    AMOUNT,
    VOLUME,
    WEIGHT
}