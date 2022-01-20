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
    open var countType: IngredientCountType,

    open var amount : Int,

    @ManyToOne(targetEntity = Food::class, fetch = FetchType.LAZY)
    @JoinColumn(name = "food_id")
    open var food : Food
) {
    @Id
    @GeneratedValue
    open var id: Int = 0

    open var unit : IngredientUnitType = getUnitTypeForCountType(countType)


    fun getUnitTypeForCountType(countType : IngredientCountType) : IngredientUnitType {
        val unitType = when (countType) {
            IngredientCountType.BOOLEAN -> IngredientUnitType.NO_UNIT
            IngredientCountType.AMOUNT -> IngredientUnitType.PCS
            IngredientCountType.WEIGHT -> IngredientUnitType.G
            IngredientCountType.VOLUME -> IngredientUnitType.ML
        }

        return unitType
    }

}