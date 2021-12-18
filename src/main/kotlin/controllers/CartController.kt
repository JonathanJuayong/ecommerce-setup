package controllers

import entities.Cart
import entities.Product
import loaders.ProductLoader

/**
 * This class acts as the middleman between the main controller and cart entity
 */
class CartController {
    private val cart = Cart()
    private val cartItems = cart.cartItems
    private val productsList = ProductLoader.products

    /**
     * This function fetches the product object from productsList using its id
     */
    private fun getProductObjectById(productId: Int): Product =
        productsList.find { it.productId == productId }!!

    /**
     * This function updates the qty of an item in the cart by removing the product id
     * from the cart and then adding it back with the updated qty
     */
    private fun updateCartItemQty(cartItem: Pair<Int, Int>, qty: Int) {
        removeProduct(cartItem.first)
        cartItems.add(cartItem.copy(second = cartItem.second + qty))
    }

    /**
     * This function returns a list of products by iterating over the cartItems and
     * fetching each product by id, along with updating its qty
     */
    fun getCartItems(): List<Product> =
        cartItems.map { (id, qty) ->
            val product = getProductObjectById(id)
            product.qty = qty
            product
        }

    /**
     * This function checks if the id is already in the cart or not. If it's not, then it adds the
     * id and qty to cartItems. Else, it calls updateCartItemQty
     */
    fun addProductId(productId: Int, qty: Int) {
        val cartItem = cartItems.find { (id, _) -> id == productId }
        if (cartItem == null) {
            cartItems.add(Pair(productId, qty))
        } else {
            updateCartItemQty(cartItem, qty)
        }
    }

    /**
     * This function removes the product id from cartItems
     */
    fun removeProduct(productId: Int) {
        cartItems.removeIf { (id, _) -> id == productId }
    }

    /**
     * This function computes the total of the cart by calling getCartItems and then
     * multiplying price with qty and adding up the total
     */
    fun getTotal(): Int {
        var total = 0
        val itemsWithQty = getCartItems()
        itemsWithQty.forEach { product ->
            total += product.price * product.qty
        }
        return total
    }

    /**
     * This function resets the cart to its original state
     */
    fun resetCart() {
        cart.total = 0
        cartItems.clear()
    }
}