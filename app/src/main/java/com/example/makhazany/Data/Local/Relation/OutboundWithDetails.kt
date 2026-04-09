package com.example.makhazany.data.local.relation

import androidx.room.Embedded
import androidx.room.Relation
import com.example.makhazany.data.local.entity.OutboundDetailsEntity
import com.example.makhazany.data.local.entity.OutboundEntity

data class OutboundWithDetails(
    @Embedded
    val outbound: OutboundEntity,

    @Relation(
        parentColumn = "id",
        entityColumn = "outboundId"
    )
    val details: List<OutboundDetailsEntity>
)
