package entities

import interfaces.CartInterface

class Cart(
    override var productsList: MutableList<Product> = mutableListOf()
) : CartInterface {
    //ADD TO CART
    fun addProduct(product: Product) {
        productsList.add(product)
    }

    //REMOVE TO CART
    fun removeProduct(product: Product) {
        productsList.remove(product)
    }


    //TOTAL CART
    fun totalCart(): Int {
        var total = 0
        productsList.forEach() {
            total += it.price
        }
        return total
    }
}