package com.example.makhazany.Data.Local.Relation

import androidx.room.Embedded
import androidx.room.Relation
import com.example.makhazany.Data.Local.Entity.ItemEntity
import com.example.makhazany.Data.Local.Entity.StockEntity

data class ItemWithStock(
    @Embedded val item: ItemEntity,
    @Relation(
        parentColumn = "id",
        entityColumn = "itemId"
    )
    val stock: StockEntity?
)
