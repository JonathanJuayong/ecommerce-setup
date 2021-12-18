package interfaces

interface CartInterface {
    var cartItems: MutableList<Pair<Int, Int>>
    var total: Int
}