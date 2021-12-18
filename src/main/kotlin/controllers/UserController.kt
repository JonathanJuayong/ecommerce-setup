package controllers

import entities.user.Member
import entities.user.NonMember
import entities.user.User
import loaders.MemberLoader
import utils.Utils
import views.UserView

/**
 * This class acts as the middleman between the user entity and the main controller
 */
class UserController {
    private var user: User? = null
    private var members: List<Member> = mutableListOf<Member>() + MemberLoader.members.toMutableList()
    private val userView = UserView()

    // helper function to capture and validate user input
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

    // helper function to capture and validate user input
    private fun askForAddress(): String =
        Utils.askForInput(
            "Please enter your address",
            "Please do not leave the address blank or empty",
        )

    // helper function to capture and validate user input
    private fun askForLogInOrSignUpOption(): String =
        Utils.askForInput(
            "",
            "Please select either (l) or (s)",
            { it in listOf("l", "s") }
        )

    /**
     * This function asks the user for name and email and then looks it up on the members list.
     */
    private fun logIn() {
        val (username, email) = askForUsernameAndEmail()
        val member = getMember(username, email)
        if (member == null)
            userView.displayError("Invalid username or email")
        else
            user = member
    }

    /**
     * This function sets the user to a non-member object with a null address
     */
    private fun signUp() {
        val (username, email) = askForUsernameAndEmail()
        user = NonMember(username, email, null)
    }

    /**
     * This function fetches the members list with a username and email
     */
    private fun getMember(username: String, email: String): Member? =
        members.find { it.username == username && it.email == email }

    /**
     * Prompts the user for an address and then sets it as the user object property
     */
    private fun setUserAddress(): String {
        val address = askForAddress()
        user?.address = address
        return address
    }

    /**
     * This function checks if the user is a member or not. If it is, it fetches the discount rate.
     * Else, it returns 0 as the discount rate.
     */
    fun getMemberDiscount(): Int {
        val currentUser = user
        return if (currentUser is Member)
            currentUser.discountRate
        else
            0
    }

    /**
     * This function returns the user address if it exists, or calls the setUserAddress function
     */
    fun getUserAddress() =
        user?.address ?: setUserAddress()


    /**
     * This function asks the user to either log in or sign up and displays a message
     * whether the user is a member or not
     */
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


