package controllers

import entities.*
import loaders.ProductLoader
import utils.Utils
import views.MainView

class MainController {
    private val view = MainView()
    private val cart = Cart()
    private val productsList = ProductLoader.products

    private fun askForOptions(): String =
        Utils.askForInput(
            "Please select your option",
            "Invalid option",
            { it in listOf("a", "b", "c", "e") }
        )

    private fun askForCartOptions(): String =
        Utils.askForInput(
            "",
            "Invalid input",
            { it in listOf("d", "b") }
        )

    private fun askForProductId(productsList: List<Product>): String =
        Utils.askForInput(
            "Enter product ID",
            "Invalid ID",
            { it.toIntOrNull() != null },
            { it.toInt() in productsList.map { product -> product.productId } }
        )

    private fun runAddToCart() {
        val prodID = askForProductId(productsList).toInt()
        cart.addProduct(prodID)
        println("Item has been added to your cart.")
    }

    private fun runViewCart() {
        val productsList = cart.cartProducts

        if (productsList.isEmpty()) {
            println("Cart is empty")
            return
        }

        view.displayCart(cart)
        val cartMenuOptions = listOf(
            "Enter (d) to delete product",
            "Enter (b) to go back"
        )
        view.displayCartMenu(cartMenuOptions)
        when (askForCartOptions()) {
            "d" -> {
                val id = askForProductId(productsList).toInt()
                val product = cart.cartProducts.find { it.productId == id }!!
                cart.removeProduct(product)
                println("${product.productName} has been successfully removed from cart")
            }
            "b" -> return
        }
    }

    private fun runCheckout() {
        if (cart.cartProducts.isEmpty()) {
            println("Cart is empty")
            return
        }

        println("Your total amount is PHP ${cart.getTotal()}")
        println("Items will be delivered in 7 days. Cash on delivery.")
        println()
        cart.resetCart()
    }

    private fun runExit() {
        println("Thank you for shopping!")
    }

    fun runApp() {
        val mainMenuOptions = listOf(
            "a - add product to cart",
            "b - view cart",
            "c - checkout",
            "e - exit"
        )

        while (true) {
            view.displayMainMenu(productsList, mainMenuOptions)
            when (askForOptions()) {
                "a" -> runAddToCart()
                "b" -> runViewCart()
                "c" -> runCheckout()
                "e" -> {
                    runExit()
                    break
                }
            }
        }
    }
}