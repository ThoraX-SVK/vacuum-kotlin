package com.vacuumlabs.learning.entity.tag

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