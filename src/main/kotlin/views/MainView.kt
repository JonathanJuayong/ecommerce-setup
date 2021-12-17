package views

import entities.Cart
import entities.Product

class MainView {
    fun displayWelcomeMessage() {
        println("Hello! Welcome to the store.")
    }

    fun displayOptions(options: Iterable<String>) {
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

    fun displayCheckout(cartTotal: Int, address: String, discount: Int = 0) {
        if (discount > 0) {
            println("Member discount applied: PHP $discount")
        }
        println("Your final amount is PHP $cartTotal")
        println("Items will be delivered to your address at $address in 7 days.")
        println("Cash on delivery.")
        println()
    }

    fun genericDisplayTable(items: List<Product>, forCart: Boolean = false) {
        val headers = mutableListOf("ID", "Product Name", "Price")
        if (forCart) headers.add("Qty")
        val placeholder = createPlaceholder(*headers.toTypedArray())

        println("------------------------------")
        displayOnPlaceholder(placeholder, *headers.toTypedArray())
        println("------------------------------")

         // print items
        items.forEach {
            val qty = if (forCart) "${it.qty}" else ""
            displayOnPlaceholder(placeholder, it.productId, it.productName, "P ${it.price}", qty)
        }
        println("------------------------------")
    }
}