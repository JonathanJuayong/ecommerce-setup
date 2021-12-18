package utils

/**
 * Class that holds utility functions
 */
class Utils {
    companion object {
        /**
         * This function accepts vararg of functions called predicates which has a type of (String) -> Boolean.
         * It checks if the string provided passes all the predicate functions
         */
        private fun validateString(string: String, vararg predicates: (String) -> Boolean): Boolean =
            predicates.all { it(string) }

        /**
         * This is a helper function that captures user input using readln and validates the user based on
         * the given predicates. If the input is invalid, it uses recursion to ask the user again until
         * it is given a valid input.
         */
        fun askForInput(
            displayMessage: String,
            errorMessage: String,
            vararg predicates: (String) -> Boolean
        ): String {
            println(displayMessage)
            val userResponse = readln().trim()
            return when {
                validateString(userResponse, *predicates, { it.isNotBlank() }, { it.isNotEmpty() }) -> {
                    println()
                    userResponse
                }
                else -> {
                    println(errorMessage)
                    println()
                    askForInput(displayMessage, errorMessage, *predicates)
                }
            }
        }

        /**
         * This helper function checks if a condition is true before running the function parameter
         */
        fun validateBeforeRunning(condition: Boolean, errorMessage: String, function: () -> Unit) {
            if (condition)
                function()
            else
                println(errorMessage)
            return
        }
    }
}