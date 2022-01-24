package com.vacuumlabs.learning.entity.tag

import com.vacuumlabs.learning.entity.EntityId
import javax.persistence.*

@Entity
open class FoodTag : EntityId() {

    open lateinit var name : String
}