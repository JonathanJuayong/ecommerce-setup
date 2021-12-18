package entities.user

private const val discount = 5

class Member(
    username: String,
    email: String,
    address: String
) : User(username, email, address) {
    val discountRate = discount
}