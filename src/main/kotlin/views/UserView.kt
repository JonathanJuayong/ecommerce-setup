package views

class UserView {
    fun displayLogInOrSignUp() {
        println("Please (l)og in or (s)ign up")
    }

    fun displayError(message: String) {
        println(message)
    }

    fun displayWelcomeMessage(username: String) {
        println("Welcome to the Store, $username!")
    }

    fun displayMemberMessage(discount: Int) {
        println("It's really good to see you again!")
        println("Enjoy your P$discount discount off every purchase!")
    }
}