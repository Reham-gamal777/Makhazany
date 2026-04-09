package com.example.makhazany.data.local.relation

import androidx.room.Embedded
import androidx.room.Relation
import com.example.makhazany.data.local.entity.ReturnedDetailsEntity
import com.example.makhazany.data.local.entity.ReturnedEntity

data class ReturnedWithDetails(
    @Embedded
    val returned: ReturnedEntity,

    @Relation(
        parentColumn = "id",
        entityColumn = "returnedId"
    )
    val details: List<ReturnedDetailsEntity>
)
