package com.vacuumlabs.learning.ingredient

import javax.persistence.*

/**
 *  Source of knowledge: https://www.jpa-buddy.com/blog/best-practices-and-common-pitfalls/
 */

@Entity
open class Ingredient(
    open var name: String,

    @Enumerated(EnumType.STRING)
    open var countType: IngredientCountType
) {
    @Id
    @GeneratedValue
    open var id: Int = 0
}