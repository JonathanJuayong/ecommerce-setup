package controllers

import entities.user.Member
import entities.user.NonMember
import entities.user.User
import loaders.MemberLoader
import utils.Utils
import views.UserView
class UserController {
    private var user: User? = null
    private var members: List<Member> = mutableListOf<Member>() + MemberLoader.members.toMutableList()
    private val userView = UserView()

    private fun askForUsernameAndEmail(): Pair<String, String> {
        val username = Utils.askForInput(
            "Please enter username",
            "Blank or empty username not allowed"
        )
        val email = Utils.askForInput(
            "Please enter email",
            "Blank or empty email not allowed"
        )
        return Pair(username, email)
    }

    private fun askForAddress(): String =
        Utils.askForInput(
            "Please enter your address",
            "Please do not leave the address blank or empty",
        )

    private fun askForLogInOrSignUpOption(): String =
        Utils.askForInput(
            "",
            "Please select either (l) or (s)",
            { it in listOf("l", "s") }
        )

    private fun logIn() {
        val (username, email) = askForUsernameAndEmail()
        val member = getMember(username, email)
        if (member == null)
            userView.displayError("Invalid username or email")
        else
            user = member
    }

    private fun signUp() {
        val (username, email) = askForUsernameAndEmail()
        user = NonMember(username, email, null)
    }

    private fun getMember(username: String, email: String): Member? =
        members.find { it.username == username && it.email == email }

    fun getMemberDiscount(): Int {
        val currentUser = user
        return if (currentUser is Member)
            currentUser.discountRate
        else
            0
    }

    private fun setUserAddress(): String {
        val address = askForAddress()
        user?.address = address
        return address
    }

    fun getUserAddress() =
        user?.address ?: setUserAddress()


    fun logInOrSignUp() {
        while (user == null) {
            userView.displayLogInOrSignUp()
            when (askForLogInOrSignUpOption()) {
                "s" -> signUp()
                "l" -> logIn()
            }
        }
        val currentUser = user
        if (currentUser != null) {
            userView.displayWelcomeMessage(currentUser.username)
        }
        if (currentUser is Member) {
            userView.displayMemberMessage(currentUser.discountRate)
        }
    }

}


