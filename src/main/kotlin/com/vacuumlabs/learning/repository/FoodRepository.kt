package com.vacuumlabs.learning.repository

import com.vacuumlabs.learning.entity.food.Food
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface FoodRepository : JpaRepository<Food, Int> {

    fun findAllByNameStartsWith(name : String) : List<Food>

}