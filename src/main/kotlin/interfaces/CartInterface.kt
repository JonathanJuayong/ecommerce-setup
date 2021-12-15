package interfaces

import entities.Product

interface CartInterface {
    var cartProducts: MutableList<Product>
}