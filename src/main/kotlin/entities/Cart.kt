package entities

import interfaces.CartInterface

class Cart(
    override var cartProducts: MutableList<Product> = mutableListOf()
) : CartInterface {
    private var productsList = listOf<Product>(
        Product("Moby", 10, 1),
        Product("Milo", 10, 2),
        Product("Cheezy", 10, 3),
        Product("Nova", 10, 4),
        Product("Piattos", 10, 5),
    )
    private var total = 0

    fun getProductsList(): List<Product> =
        productsList

    //ADD TO CART
    fun addProduct(product: Product) {
        cartProducts.add(product)
    }

    fun addProduct(productId: Int) {
        val product = productsList.find {it.productId == productId}
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
}