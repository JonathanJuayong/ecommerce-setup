package controllers

import entities.Cart
import entities.Product
import loaders.ProductLoader

class CartController {
    private val cart = Cart()
    private val cartItems = cart.cartItems
    private val productsList = ProductLoader.products

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
    fun addProductId(productId: Int, qty: Int) {
        val cartItem = cartItems.find { (id, _) -> id == productId }
        if (cartItem == null) {
            cartItems.add(Pair(productId, qty))
        } else {
            updateCartItemQty(cartItem, qty)
        }
    }

    //REMOVE TO CART
    fun removeProduct(productId: Int) {
        cartItems.removeIf { (id, _) -> id == productId }
    }

    //TOTAL CART
    fun getTotal(): Int {
        var total = 0
        val itemsWithQty = getCartItems()
        itemsWithQty.forEach { product ->
            total += product.price * product.qty
        }
        return total
    }

    //RESET CART
    fun resetCart() {
        cart.total = 0
        cartItems.clear()
    }
}