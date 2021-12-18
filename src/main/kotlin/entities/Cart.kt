package entities

import interfaces.CartInterface
import loaders.ProductLoader

class Cart : CartInterface {
    override var cartItems: MutableList<Pair<Int, Int>> = mutableListOf() // Pair<productId, qty>
    override var total = 0
}

