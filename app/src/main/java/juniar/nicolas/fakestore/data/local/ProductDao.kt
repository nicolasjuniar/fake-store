package juniar.nicolas.fakestore.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import juniar.nicolas.fakestore.data.model.ProductLocal


@Dao
interface ProductDao {

    @Query("DELETE FROM product")
    fun deleteAllProduct()

    @Query("DELETE FROM product WHERE id = :id")
    fun deleteSingleProduct(id : Int)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertSingleProduct(product: ProductLocal)

    @Query("SELECT * FROM product")
    fun getAllProduct() : List<ProductLocal>

}