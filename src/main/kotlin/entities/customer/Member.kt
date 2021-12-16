package entities.customer

import entities.customer.Customer

class Member(
    validID: String,
    firstName: String,
    lastName: String
) : Customer(validID, firstName, lastName) {
    val discountRate = 5
}