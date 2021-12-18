package entities

import interfaces.CartInterface
import loaders.ProductLoader

class Cart : CartInterface {
    private val productsList = ProductLoader.products
    private var total = 0
    private var cartItems: MutableList<Pair<Int, Int>> = mutableListOf() // Pair<productId, qty>

    private fun getProductObjectById(productId: Int): Product =
        productsList.find { it.productId == productId }!!

    private fun updateCartItemQty(cartItem: Pair<Int, Int>, qty: Int) {
        removeProduct(cartItem.first)
        cartItems.add(cartItem.copy(second = cartItem.second + qty))
    }

    fun getCartItems() =
        cartItems.map { (id, qty) ->
            val product = getProductObjectById(id)
            product.qty = qty
            product
        }

    //ADD TO CART
    override fun addProductId(productId: Int, qty: Int) {
        val cartItem = cartItems.find { (id, _) -> id == productId }
        if (cartItem == null) {
            cartItems.add(Pair(productId, qty))
        } else {
            updateCartItemQty(cartItem, qty)
        }
    }

    //REMOVE TO CART
    override fun removeProduct(productId: Int) {
        cartItems.removeIf { (id, _) -> id == productId }
    }

    //TOTAL CART
    override fun getTotal(): Int {
        var total = 0
        val itemsWithQty = getCartItems()
        itemsWithQty.forEach { product ->
            total += product.price * product.qty
        }
        return total
    }

    //RESET CART
    override fun resetCart() {
        total = 0
        cartItems.clear()
    }
}

