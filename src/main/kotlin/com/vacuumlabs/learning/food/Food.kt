package com.vacuumlabs.learning.food

import com.vacuumlabs.learning.food.tag.FoodTag
import com.vacuumlabs.learning.ingredient.Ingredient
import javax.persistence.*

@Entity
open class Food(
    open var name: String,

    @OneToMany(targetEntity = Ingredient::class, cascade = [CascadeType.ALL], fetch = FetchType.LAZY, mappedBy = "food", orphanRemoval = true)
    open var ingredients : MutableList<Ingredient>,

    @Column(nullable = true)
    @ManyToMany(targetEntity = FoodTag::class, cascade = [CascadeType.MERGE], fetch = FetchType.LAZY)
    @JoinTable(
        name = "FOOD_to_TAGS",
        joinColumns = [JoinColumn(name = "FOOD_ID", referencedColumnName = "id")],
        inverseJoinColumns = [JoinColumn(name = "TAG_ID", referencedColumnName = "id")]
    )
    open var tags : MutableSet<FoodTag>
) {

    constructor() : this("", mutableListOf(), mutableSetOf()) {}

    @Id
    @GeneratedValue
    open var id: Int = 0
}