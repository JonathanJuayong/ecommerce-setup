package entities

import interfaces.ProductInterface

data class Product(
    override val productName: String,
    override val price: Int,
    override val productId: Int
) : ProductInterface {

    override fun getProdName(): String {
        return productName
    }

    override fun getProdPrice(): Int {
        return price
    }

    override fun getProdId(): Int {
        return productId
    }

}