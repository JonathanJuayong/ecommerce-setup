package entities

import interfaces.ProductInterface

data class Product(
    override val productName: String,
    override val price: Int,
    override val productId: Int,
    var qty: Int = 0
) : ProductInterface {
}