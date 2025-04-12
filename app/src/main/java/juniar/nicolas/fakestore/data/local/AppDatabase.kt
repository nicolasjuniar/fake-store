package juniar.nicolas.fakestore.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import juniar.nicolas.fakestore.data.model.ProductLocal


@Database(entities = [ProductLocal::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun productDao(): ProductDao
}