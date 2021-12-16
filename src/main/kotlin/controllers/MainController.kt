package controllers

import entities.*
import entities.customer.Customer
import entities.customer.Member
import loaders.ProductLoader
import utils.Utils
import views.MainView

class MainController {
    private var customer: Customer? = null
    private val view = MainView()
    private val productsList = ProductLoader.products
    private val cart = Cart()

    private fun askForOptions(): String =
        Utils.askForInput(
            "Please select your option",
            "Invalid option",
            { it in listOf("a", "b", "c", "e") }
        )

    private fun askForProductId(productsList: List<Product>): String =
        Utils.askForInput(
            "Enter product ID",
            "Invalid ID",
            { it.toIntOrNull() != null },
            { it.toInt() in productsList.map { product -> product.productId } }
        )

    private fun displayCheckout(cart: Cart) {
        if (cart.cartProducts.isEmpty()) {
            println("Cart is empty")
            return
        }
        var totalBill = cart.totalCart()
        val currentCustomer = customer

        if (currentCustomer is Member) {
            val discount = currentCustomer.discountRate
            println("As a member, you have an additional discount of PHP $discount")
            totalBill -= discount
        }
        println("Your total amount is PHP $totalBill")
        println("Items will be delivered in 7 days. Cash on delivery.")
        println()
        cart.resetCart()
    }

    private fun askForCartOptions(): String =
        Utils.askForInput(
            "",
            "Invalid input",
            { it in listOf("d", "b") }
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
                "c" -> displayCheckout(cart)
                "e" -> {
                    println("Thank you for shopping!")
                    break
                }
            }
        }
    }
}