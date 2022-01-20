package com.vacuumlabs.learning.food

import com.vacuumlabs.learning.ingredient.Ingredient
import javax.persistence.*

@Entity
open class Food(
    open var name: String,

    @OneToMany(targetEntity = Ingredient::class, cascade = [CascadeType.ALL], fetch = FetchType.LAZY, mappedBy = "food")
    open var ingredients : MutableList<Ingredient>

) {

    @Id
    @GeneratedValue
    open var id: Int = 0
}