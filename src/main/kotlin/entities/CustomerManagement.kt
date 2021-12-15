package entities

import utils.Utils

class CustomerManagement {
    val customerList = mutableListOf<Customer>(
        Customer("1", "fj", "jf")
    )

    fun generateID(name: String): String {
        return (0..100000).random().toString() + name.substring(0, 2).uppercase()
    }
    fun generateCustomer(name: String, lastName: String): Customer{
        return Customer(generateID(name), name, lastName)
    }
    fun addCustomer(): Customer? {
        println("Please enter customerName: ")
        val customerName = readln()!!.toString()
        println("Please enter customerLastName: ")
        val customerLastName = readln()!!.toString()
        customerList.add(generateCustomer(customerName, customerLastName))
        val customerData = customerList.find { item -> item.firstName == customerName }!!
        println("Welcome ${customerData.firstName} ${customerData.lastName} to the app.")
        println()
        return customerData
    }
    fun getCustomer(): Customer? {
        val customer = Utils.askForInput(
            "Please enter customerName: ",
            "CustomerName isn't register yet.",
            { customerList.contains(customerList.find { item -> item.firstName == it }) }
        )
        val customerData = customerList.find { item -> item.firstName == customer }!!
        println("Welcome ${customerData.firstName} ${customerData.lastName} to the app.")
        println()
        return customerData
    }
}