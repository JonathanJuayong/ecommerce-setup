package entities

import interfaces.CartInterface
import loaders.ProductLoader

class Cart: CartInterface {
    override var cartProducts: MutableList<Product> = mutableListOf()
    private val productsList = ProductLoader.products

    private var total = 0

    //ADD TO CART
    fun addProduct(productId: Int) {
        val product = productsList.find { it.productId == productId }
        if (product != null) {
            cartProducts.add(product)
        } else {
            println("Product ID does not exist")
        }
    }

    //REMOVE TO CART
    fun removeProduct(product: Product) {
        cartProducts.remove(product)
    }

    //TOTAL CART
    fun totalCart(): Int {
        cartProducts.forEach() {
            total += it.price
        }
        return total
    }

    //RESET CART
    fun resetCart() {
        total = 0
        cartProducts.clear()
    }
}