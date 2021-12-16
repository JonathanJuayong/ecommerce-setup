package views

import entities.Cart
import entities.Product

class MainView {
    fun displayWelcomeMessage() {
        println("Hello! Welcome to the store.")
    }

    fun displayMainMenu(productsList: List<Product>, options: List<String>) {
        println()
        displayProducts(productsList)
        options.forEach {
            println(it)
        }
    }

    fun displayCartMenu(options: List<String>) {
        options.forEach {
            println(it)
        }
    }

    private fun createPlaceholder(vararg string: String): String {
        var row = "|"
        string.forEach {
            val length = it.length
            row += " %-${length}s |"
        }
        return row
    }

    private fun displayOnPlaceholder(placeholder: String, vararg items: Any) {
        println(placeholder.format(*items))
    }

    private fun displayProducts(productsList: List<Product>) {
        // print column headers
        val headers = arrayOf("ID", "Product Names", "Price")
        val placeholder = createPlaceholder(*headers)

        println("------------------------------")
        displayOnPlaceholder(placeholder, *headers)
        println("------------------------------")

        // print items
        productsList.forEach {
            displayOnPlaceholder(placeholder, it.productId, it.productName, "P ${it.price}")
        }
        println("------------------------------")
    }

    fun displayCart(cart: Cart) {
        val headers = arrayOf("ID", "Product Name", "Price", "Qty")
        val placeholder = createPlaceholder(*headers)

        println("----------------------------------")
        displayOnPlaceholder(placeholder, *headers)
        println("----------------------------------")

        cart.getCartItems().forEach { (product, qty) ->
            displayOnPlaceholder(placeholder, product.productId, product.productName, "P ${product.price}", qty)
        }
        println("----------------------------------")
    }
}