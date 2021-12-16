package entities.user

class Member(
    username: String,
    email: String,
    address: String
) : User(username, email, address) {
    val discountRate = 5
}