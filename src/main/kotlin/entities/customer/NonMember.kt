package entities.customer

class NonMember(
    validID: String,
    firstName: String,
    lastName: String
) : Customer(validID, firstName, lastName) {

}