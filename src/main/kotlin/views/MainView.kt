package views

import entities.Cart
import entities.Product

class MainView {
    fun displayMainMenu(productsList: List<Product>, options: List<String>) {
        println("Hello! Welcome to the store.")
        println()
        formatToTable(productsList)
        options.forEach {
            println(it)
        }
    }

    fun displayCart(cart: Cart) {
        formatToTable(cart.cartProducts)
    }

    fun displayCartMenu(options: List<String>) {
        options.forEach {
            println(it)
        }
    }

    private fun formatToTable(productsList: List<Product>) {
        // print column headers
        println("------------------------------")
        println("| %-2s | %-8s | %6s |".format("ID", "Product Name", "Price"))
        println("------------------------------")

        // print items
        productsList.forEach {
            println("| %-2d | %-12s | PHP %2d |".format(it.productId, it.productName, it.price))
        }
        println("------------------------------")
    }
}