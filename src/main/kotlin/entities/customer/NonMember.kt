package entities.customer

import entities.customer.Customer

class NonMember(
    validID: String,
    firstName: String,
    lastName: String
) : Customer(validID, firstName, lastName) {

}