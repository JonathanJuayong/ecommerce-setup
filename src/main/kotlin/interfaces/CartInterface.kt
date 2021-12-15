package interfaces

import entities.Product

interface CartInterface {
    var productsList: MutableList<Product>
}