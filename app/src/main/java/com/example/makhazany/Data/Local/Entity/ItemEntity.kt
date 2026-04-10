package com.example.makhazany.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "items")
data class ItemEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int ,
    val name: String,
    val description: String?,
    val price: Double,
    val category: String?,
    val sku: String?

)
