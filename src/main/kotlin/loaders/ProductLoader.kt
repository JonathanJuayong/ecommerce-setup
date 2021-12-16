package loaders

import entities.Product

class ProductLoader {
    companion object {
        val products = listOf(
            Product("Moby", 10, 1),
            Product("Milo", 10, 2),
            Product("Cheezy", 10, 3),
            Product("Nova", 10, 4),
            Product("Piattos", 10, 5),
        )
    }
}