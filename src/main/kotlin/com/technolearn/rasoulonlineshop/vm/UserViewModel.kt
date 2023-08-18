package com.technolearn.rasoulonlineshop.vm

import com.technolearn.rasoulonlineshop.models.customers.Customer
import com.technolearn.rasoulonlineshop.models.customers.User

data class UserViewModel(
    var id: Long,
    var username: String = "",
    var oldPassword: String = "",
    var password: String = "",
    var repeatPassword: String = "",
    var email: String = "",
    var firstName: String = "",
    var lastName: String = "",
    var phone: String = "",
    var addressname: String = "",
    var address: String = "",
    var city: String = "",
    var province: String = "",
    var postalCode: String = "",
    var country: String = "",
    var customerId: Long = 0,
) {
    fun convertToUser(): User {
        return User(
            id = id,
            userName = username,
            password = password,
            email = email,
            customer = convertToCustomer()
        )
    }

    private fun convertToCustomer(): Customer {
        return Customer(
            id = customerId,
            firstName = firstName,
            lastName = lastName,
            phone = phone,
            addressName = addressname,
            address = address,
            city = city,
            province = province,
            postalCode = postalCode,
            country = country,
            )
    }

}
