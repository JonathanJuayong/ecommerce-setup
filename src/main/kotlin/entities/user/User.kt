package entities.user

import interfaces.UserInterface

abstract class User(
    override var validID: String = "",
    override var firstName: String = "",
    override var lastName: String = "",
): UserInterface



