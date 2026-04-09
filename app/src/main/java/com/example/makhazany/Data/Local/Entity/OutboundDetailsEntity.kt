package com.example.makhazany.data.local.entity

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "outbound_details",
    foreignKeys = [
        ForeignKey(
            entity = OutboundEntity::class,
            parentColumns = ["id"],
            childColumns = ["outboundId"],
            onDelete = ForeignKey.CASCADE
        ),
        ForeignKey(
            entity = ItemEntity::class,
            parentColumns = ["id"],
            childColumns = ["itemId"],
            onDelete = ForeignKey.CASCADE
        )
    ],
    indices = [
        Index("outboundId"),
        Index("itemId")
    ]
)
data class OutboundDetailsEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val outboundId: Int,
    val itemId: Int,
    val quantity: Double,
    val unitPrice: Double
)
