package com.vacuumlabs.learning.repository

import com.vacuumlabs.learning.food.tag.FoodTag
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface FoodTagRepository : JpaRepository<FoodTag, Int> {

}