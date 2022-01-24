package com.vacuumlabs.learning.entity.ingredient

import com.fasterxml.jackson.annotation.JsonIgnore
import com.vacuumlabs.learning.entity.EntityId
import com.vacuumlabs.learning.entity.food.Food
import javax.persistence.*

/**
 *  Source of knowledge: https://www.jpa-buddy.com/blog/best-practices-and-common-pitfalls/
 *  https://medium.com/@rajibrath20/the-best-way-to-map-a-onetomany-relationship-with-jpa-and-hibernate-dbbf6dba00d3
 *  Webinar Kotlin + JPA: https://www.youtube.com/watch?v=a_6V8xwiv04
 *
 */

@Entity
open class Ingredient : EntityId() {
    open lateinit var name: String

    @Enumerated(EnumType.STRING)
    open lateinit var unit : IngredientUnitType

    open var amount : Int = 0

    @ManyToOne(targetEntity = Food::class, fetch = FetchType.LAZY)
    @JoinColumn(name = "food_id")
    @JsonIgnore
    open lateinit var food : Food
}