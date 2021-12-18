package interfaces

interface CartInterface {
    fun addProductId(productId: Int, qty: Int)
    fun removeProduct(productId: Int)
    fun getTotal(): Int
    fun resetCart()
}