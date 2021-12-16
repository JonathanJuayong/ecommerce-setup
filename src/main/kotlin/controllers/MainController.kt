package controllers

import entities.*
import loaders.ProductLoader
import utils.Utils
import views.MainView

class MainController {
    private val view = MainView()
    private val cart = Cart()
    private val productsList = ProductLoader.products
    private val user = UserController()

    private fun askForMainMenuOptions(): String =
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

    private fun askForProductQty(): String =
        Utils.askForInput(
            "Please enter a qty (1-99)",
            "Invalid amount",
            { it.toIntOrNull() != null },
            { it.toInt() in (1..99)}
        )

    private fun validateBeforeRunning(condition: Boolean, errorMessage: String, function: () -> Unit) {
        if (condition)
            function()
        else
            println(errorMessage)
        return
    }

    private fun runAddToCart() {
        val prodID = askForProductId(productsList).toInt()
        val prodQty = askForProductQty().toInt()
        cart.addProductId(prodID, prodQty)
        println("Item has been added to your cart.")
    }

    private fun runViewCart() {
        val productsList = cart.getCartItems().map {(product) -> product}
        view.displayCart(cart)
        val cartMenuOptions = listOf(
            "Enter (d) to delete product",
            "Enter (b) to go back"
        )
        view.displayCartMenu(cartMenuOptions)
        when (askForCartOptions()) {
            "d" -> {
                val id = askForProductId(productsList).toInt()
                cart.removeProduct(id)
                println("Item has been successfully removed from cart")
            }
            "b" -> return
        }
    }

    private fun runCheckout() {
        println("Your total amount is PHP ${cart.getTotal()}")
        println("Items will be delivered in 7 days. Cash on delivery.")
        println()
        cart.resetCart()
    }

    private fun runExit() {
        println("Thank you for shopping!")
    }

    fun runApp() {
        user.logInOrSignUp()

        val mainMenuOptions = listOf(
            "a - add product to cart",
            "b - view cart",
            "c - checkout",
            "e - exit"
        )

        while (true) {
            view.displayMainMenu(productsList, mainMenuOptions)
            val cartIsNotEmpty = cart.getCartItems().isNotEmpty()
            when (askForMainMenuOptions()) {
                "a" -> runAddToCart()
                "b" -> validateBeforeRunning(
                    cartIsNotEmpty,
                    "Please add products first",
                    ::runViewCart
                )
                "c" -> validateBeforeRunning(
                    cartIsNotEmpty,
                    "Cannot checkout with empty cart",
                    ::runCheckout
                )
                "e" -> {
                    runExit()
                    break
                }
            }
        }
    }
}