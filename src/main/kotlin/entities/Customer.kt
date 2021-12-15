package entities

abstract class Customer(
    var validID: String = "",
    var firstName: String = "",
    var lastName: String = "",
)

class Member(
    validID: String,
    firstName: String,
    lastName: String
) : Customer(validID, firstName, lastName) {
    val discountRate = 5
}

class NonMember(
    validID: String,
    firstName: String,
    lastName: String
) : Customer(validID, firstName, lastName) {

}