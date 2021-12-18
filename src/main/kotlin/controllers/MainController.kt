package controllers

import entities.*
import loaders.ProductLoader
import utils.Utils
import views.MainView

/**
 * The main class that integrates all other controller classes and view classes
 */
class MainController {
    private val user = UserController()
    private val cart = CartController()
    private val view = MainView()
    private val productsList = ProductLoader.products

    // helper function to capture and validate user input
    private fun askForMainMenuOptions(): String =
        Utils.askForInput(
            "Please select your option",
            "Invalid option",
            { it in listOf("a", "b", "c", "e") }
        )

    // helper function to capture and validate user input
    private fun askForCartOptions(): String =
        Utils.askForInput(
            "",
            "Invalid input",
            { it in listOf("d", "b") }
        )

    // helper function to capture and validate user input
    private fun askForProductId(productsList: List<Product>): String =
        Utils.askForInput(
            "Enter product ID",
            "Invalid ID",
            { it.toIntOrNull() != null },
            { it.toInt() in productsList.map { product -> product.productId } }
        )

    // helper function to capture and validate user input
    private fun askForProductQty(): String =
        Utils.askForInput(
            "Please enter a qty (1-99)",
            "Invalid amount",
            { it.toIntOrNull() != null },
            { it.toInt() in (1..99) }
        )

    /**
     * This function adds an item to the cart by asking the user for product id and qty
     * and adds them to the cart
     */
    private fun runAddToCart() {
        val prodID = askForProductId(productsList).toInt()
        val prodQty = askForProductQty().toInt()
        cart.addProductId(prodID, prodQty)
        println("Item has been added to your cart.")
    }

    /**
     * This function displays the options the user can do when viewing the cart and asks
     * for their input
     */
    private fun runCartMenu() {
        val productsList = cart.getCartItems()
        view.displayItemsTable(productsList, true)
        val cartMenuOptions = listOf(
            "Enter (d) to delete product",
            "Enter (b) to go back"
        )
        view.displayOptions(cartMenuOptions)
        when (askForCartOptions()) {
            "d" -> {
                val id = askForProductId(productsList).toInt()
                cart.removeProduct(id)
                println("Item has been successfully removed from cart")
            }
            "b" -> return
        }
    }

    /**
     * This function asks the user for an address if non-member or will fetch the
     * user address if member. This also resets the cart
     */
    private fun runCheckoutMenu() {
        val discount = user.getMemberDiscount()
        val address = user.getUserAddress()
        val total = cart.getTotal() - discount
        view.displayCheckout(total, address, discount)
        cart.resetCart()
    }

    /**
     * This function prints a message before the program exits.
     */
    private fun runExit() {
        view.displayExitMessage()
    }

    /**
     * This function prints all the products for sale and acts as a main menu for the user
     */
    fun runApp() {
        user.logInOrSignUp()

        val mainMenuOptions = listOf(
            "a - add product to cart",
            "b - view cart",
            "c - checkout",
            "e - exit"
        )

        while (true) {
            val cartIsNotEmpty = cart.getCartItems().isNotEmpty()
            view.displayItemsTable(productsList)
            view.displayOptions(mainMenuOptions)
            when (askForMainMenuOptions()) {
                "a" -> runAddToCart()
                "b" -> Utils.validateBeforeRunning(
                    cartIsNotEmpty,
                    "Please add products first",
                    ::runCartMenu
                )
                "c" -> Utils.validateBeforeRunning(
                    cartIsNotEmpty,
                    "Cannot checkout with empty cart",
                    ::runCheckoutMenu
                )
                "e" -> {
                    runExit()
                    break
                }
            }
        }
    }
}