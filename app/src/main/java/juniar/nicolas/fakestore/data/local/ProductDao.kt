package juniar.nicolas.fakestore.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import juniar.nicolas.fakestore.data.model.ProductLocal


@Dao
interface ProductDao {

    @Query("DELETE FROM product WHERE username = :username")
    fun deleteAllProduct(username: String)

    @Query("DELETE FROM product WHERE id = :id")
    fun deleteSingleProduct(id: Int)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertSingleProduct(product: ProductLocal)

    @Query("SELECT * FROM product WHERE username = :username")
    fun getAllProduct(username: String): List<ProductLocal>

    @Query("UPDATE product SET quantity = :quantity WHERE id = :id")
    fun updateQuantity(quantity: Int, id: Int)

    @Query("SELECT quantity FROM product WHERE title = :title AND username = :username")
    fun getQuantity(title: String, username: String): Int?

    @Query("SELECT id FROM product WHERE title = :title AND username = :username")
    fun getIdProduct(title: String, username: String): Int?
}