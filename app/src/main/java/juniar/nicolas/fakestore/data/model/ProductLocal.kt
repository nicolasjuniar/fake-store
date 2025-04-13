package juniar.nicolas.fakestore.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity("product")
data class ProductLocal(
    @ColumnInfo("category")
    val category: String?,
    @ColumnInfo("description")
    val description: String?,
    @PrimaryKey
    @ColumnInfo("id")
    val id: Int,
    @ColumnInfo("image")
    val image: String?,
    @ColumnInfo("price")
    val price: Double?,
    @ColumnInfo("ratingRate")
    val ratingRate: Double?,
    @ColumnInfo("ratingCount")
    val ratingCount: Int?,
    @ColumnInfo("title")
    val title: String?,
    @ColumnInfo("quantity")
    var quantity: Int = 0,
    @ColumnInfo("username")
    val username: String = ""
)