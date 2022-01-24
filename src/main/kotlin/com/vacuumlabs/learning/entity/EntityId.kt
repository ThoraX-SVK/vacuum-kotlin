package com.vacuumlabs.learning.entity

import org.hibernate.Hibernate
import javax.persistence.*

@MappedSuperclass
open class EntityId() {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false)
    open var id: Long? = null

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || Hibernate.getClass(this) != Hibernate.getClass(other)) return false
        other as EntityId

        return id != null && id == other.id
    }

    override fun hashCode(): Int = javaClass.hashCode()
}