package com.vacuumlabs.learning.ingredient

import com.vacuumlabs.learning.food.Food
import javax.persistence.*

/**
 *  Source of knowledge: https://www.jpa-buddy.com/blog/best-practices-and-common-pitfalls/
 */

@Entity
open class Ingredient(
    open var name: String,

    @Enumerated(EnumType.STRING)
    open var unit : IngredientUnitType,

    open var amount : Int,

    @ManyToOne(targetEntity = Food::class, fetch = FetchType.LAZY)
    @JoinColumn(name = "food_id")
    open var food : Food
) {

    constructor() : this("", IngredientUnitType.NO_UNIT, 0, Food())

    @Id
    @GeneratedValue
    open var id: Int = 0
}