package juniar.nicolas.fakestore.data.model

import com.google.gson.annotations.SerializedName

data class UserModel(
    @SerializedName("address")
    val address: Address?,
    @SerializedName("email")
    val email: String?,
    @SerializedName("id")
    val id: Int?,
    @SerializedName("name")
    val name: Name?,
    @SerializedName("password")
    val password: String?,
    @SerializedName("phone")
    val phone: String?,
    @SerializedName("username")
    val username: String?
)

data class Address(
    @SerializedName("city")
    val city: String?,
    @SerializedName("number")
    val number: Int?,
    @SerializedName("street")
    val street: String?,
    @SerializedName("zipcode")
    val zipcode: String?
) {
    fun getFullAddress() = "$street $number, $city, $zipcode"
}

data class Name(
    @SerializedName("firstname")
    val firstname: String?,
    @SerializedName("lastname")
    val lastname: String?
) {
    fun getFullname() = "$firstname $lastname"
}

