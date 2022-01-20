package com.vacuumlabs.learning.repository

import com.vacuumlabs.learning.food.tag.FoodTag
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface FoodTagRepository : JpaRepository<FoodTag, Int> {

    fun findFoodTagByName(name: String) : Optional<FoodTag>
}