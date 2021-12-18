package utils

class Utils {
    companion object {
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

        fun validateBeforeRunning(condition: Boolean, errorMessage: String, function: () -> Unit) {
            if (condition)
                function()
            else
                println(errorMessage)
            return
        }

        private fun validateString(string: String, vararg predicates: (String) -> Boolean): Boolean =
            predicates.all { it(string) }
    }
}