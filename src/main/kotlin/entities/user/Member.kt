package entities.user

class Member(
    validID: String,
    firstName: String,
    lastName: String
) : User(validID, firstName, lastName) {
    val discountRate = 5
}