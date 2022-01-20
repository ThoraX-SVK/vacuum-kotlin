package com.vacuumlabs.learning.food.tag

import javax.persistence.*

@Entity
open class FoodTag(
    open var name : String,
) {

    constructor() : this("")

    @Id
    @GeneratedValue
    open var id: Int = 0
}