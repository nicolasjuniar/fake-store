package juniar.nicolas.fakestore.data.model

import com.google.gson.annotations.SerializedName
import juniar.nicolas.fakestore.util.orEmpty

data class ProductModel(
    @SerializedName("category")
    val category: String?,
    @SerializedName("description")
    val description: String?,
    @SerializedName("id")
    val id: Int?,
    @SerializedName("image")
    val image: String?,
    @SerializedName("price")
    val price: Double?,
    @SerializedName("rating")
    val rating: Rating?,
    @SerializedName("title")
    val title: String?
) {
    fun toProductLocal(quantity: Int, userId: Int) =
        ProductLocal(
            category,
            description,
            id.orEmpty(),
            image,
            price,
            rating?.rate,
            rating?.count,
            title,
            quantity,
            userId
        )
}

data class Rating(
    @SerializedName("count")
    val count: Int?,
    @SerializedName("rate")
    val rate: Double?
)