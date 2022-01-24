package com.vacuumlabs.learning.entity.food

import com.vacuumlabs.learning.entity.EntityId
import com.vacuumlabs.learning.entity.tag.FoodTag
import com.vacuumlabs.learning.entity.ingredient.Ingredient
import javax.persistence.*

@Entity
open class Food : EntityId() {

    open lateinit var name: String

    @OneToMany(targetEntity = Ingredient::class, cascade = [CascadeType.ALL], fetch = FetchType.LAZY, mappedBy = "food", orphanRemoval = true)
    open lateinit var ingredients : MutableList<Ingredient>

    @Column(nullable = true)
    @ManyToMany(targetEntity = FoodTag::class, cascade = [CascadeType.MERGE], fetch = FetchType.LAZY)
    @JoinTable(
        name = "FOOD_to_TAGS",
        joinColumns = [JoinColumn(name = "FOOD_ID", referencedColumnName = "id")],
        inverseJoinColumns = [JoinColumn(name = "TAG_ID", referencedColumnName = "id")]
    )
    open lateinit var tags : MutableSet<FoodTag>
}