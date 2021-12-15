package entities

import utils.Utils

class EcommerceApp {
    val customer = Customer()
    private val customerManagement = CustomerManagement()

    init {
        println("Welcome to App. Choose to do: ")
        val ans1 = Utils.askForInput(
            "(A)dd as new Customer or (L)og in as registered Customer? ",
            "Invalid answer.",
            {it in listOf("A", "L")}
        )
        if (ans1 == "A") customerManagement.addCustomer()
        if (ans1 == "L") customerManagement.getCustomer()
    }

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

    private fun displayProducts(productsList: List<Product>) {
        println("Here are our products")
        println("ID | Name | Price ")
        productsList.forEach {
            println("${it.productId} | ${it.productName} | PHP ${it.price}")
        }
    }

    private fun displayCart(cart: Cart) {
        val productsList = cart.cartProducts

        if (productsList.isEmpty()) {
            println("Cart is empty")
            return
        }

        cart.cartProducts.forEach {
            println("${it.productId} - ${it.productName} PHP ${it.price}")
        }
        println("Enter (d) to delete product")
        println("Enter (b) to go back")
        val userInput = Utils.askForInput(
            "",
            "Invalid input",
            { it in listOf("d", "b") }
        )

        when (userInput) {
            "d" -> {
                val id = askForProductId(productsList).toInt()
                val product = cart.cartProducts.find { it.productId == id }!!
                cart.removeProduct(product)
                println("${product.productName} has been successfully removed from cart")
            }
            "b" -> return
        }
    }

    private fun displayCheckout(cart: Cart) {
        if (cart.cartProducts.isEmpty()) {
            println("Cart is empty")
            return
        }
        println("Your total amount is PHP ${cart.totalCart()}")
        println("Items will be delivered in 7 days. Cash on delivery.")
        println()
        cart.cartProducts.clear()
    }

    fun runApp() {
        println("Hello welcome to the store!")

        val cart1 = Cart()
        val prodList = cart1.getProductsList()

        while (true) {
            displayProducts(prodList)
            println()
            println("a - add product to cart")
            println("b - view cart")
            println("c - checkout")
            println("e - exit")
            val userOption = askForOptions()
            when (userOption) {
                "a" -> {
                    val prodID = askForProductId(prodList).toInt()
                    cart1.addProduct(prodID)
                    println("Item has been added to your cart.")
                }
                "b" -> displayCart(cart1)
                "c" -> {
                    displayCheckout(cart1)
                }
                "e" -> {
                    println("Thank you for shopping!")
                    break
                }
            }
        }
    }
}