package entities.user

import interfaces.UserInterface

abstract class User(
    override var username: String,
    override var email: String,
    override var address: String?
): UserInterface {
}



