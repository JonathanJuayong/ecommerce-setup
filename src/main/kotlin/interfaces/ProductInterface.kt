package interfaces

interface ProductInterface {
    val productName: String
    val price: Int
    val productId: Int

    fun getProdName(): String
    fun getProdPrice(): Int
    fun getProdId(): Int
}