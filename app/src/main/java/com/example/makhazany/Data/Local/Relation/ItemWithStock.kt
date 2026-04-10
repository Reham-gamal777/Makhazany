package com.example.makhazany.data.local.relation

import androidx.room.Embedded
import androidx.room.Relation
import com.example.makhazany.data.local.entity.ItemEntity
import com.example.makhazany.data.local.entity.StockEntity

data class ItemWithStock(
    @Embedded val item: ItemEntity,
    @Relation(
        parentColumn = "id",
        entityColumn = "itemId"
    )
    val stock: StockEntity?
)
