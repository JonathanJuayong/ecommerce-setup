package entities.customer

import interfaces.CustomerInterface

abstract class Customer(
    override var validID: String = "",
    override var firstName: String = "",
    override var lastName: String = "",
): CustomerInterface



