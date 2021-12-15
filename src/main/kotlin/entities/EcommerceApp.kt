package entities

class EcommerceApp {

    fun runApp() {
        println("Hello welcome to the store!")

        val prodList = mutableListOf<Product>()

        prodList.add(Product("Moby", 10, 1))
        prodList.add(Product("Milo", 10, 2))
        prodList.add(Product("Cheezy", 10, 3))
        prodList.add(Product("Nova", 10, 4))
        prodList.add(Product("Piattos", 10, 5))

        val cart1 = Cart()

        // ask for user input
        val id = readln().toInt()

        // lookup product
        val product = prodList.find {it.productId == id}!!

        // add product to cart
        cart1.addProduct(product)

        cart1.addProduct(prodList[1]) // milo
        cart1.addProduct(prodList[2]) // cheezy

        println(cart1.productsList)
        cart1.removeProduct(prodList[0])
        cart1.removeProduct(prodList[2]) // nova
        println(cart1.productsList)

        println(cart1.totalCart())



        // begin loop here
        // display products
        // display options (add to cart, view cart, checkout)
        // if (add to cart) ask for id and qty
        // if (view cart) display items or delete item
        // if (checkout) compute total, ask for address, end loop
    }
}