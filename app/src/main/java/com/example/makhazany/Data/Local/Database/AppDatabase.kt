package com.example.makhazany.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.makhazany.data.local.dao.*
import com.example.makhazany.data.local.entity.*

@Database(
    entities = [
        ItemEntity::class,
        CustomerEntity::class,
        StockEntity::class,
        InboundEntity::class,
        OutboundEntity::class,
        OutboundDetailsEntity::class,
        ReturnedEntity::class,
        ReturnedDetailsEntity::class,
        PaymentEntity::class
    ],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun itemDao(): ItemDao
    abstract fun customerDao(): CustomerDao
    abstract fun stockDao(): StockDao
    abstract fun inboundDao(): InboundDao
    abstract fun outboundDao(): OutboundDao
    abstract fun outboundDetailsDao(): OutboundDetailsDao
    abstract fun returnedDao(): ReturnedDao
    abstract fun returnedDetailsDao(): ReturnedDetailsDao
    abstract fun paymentDao(): PaymentDao
}
