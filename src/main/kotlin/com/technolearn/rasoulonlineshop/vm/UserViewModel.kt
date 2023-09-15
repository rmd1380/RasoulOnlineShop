package com.technolearn.rasoulonlineshop.vm

import com.technolearn.rasoulonlineshop.models.customers.Customer
import com.technolearn.rasoulonlineshop.models.customers.User
import io.swagger.v3.oas.annotations.Hidden

data class UserViewModel(
        var id: Long=0,
        var username: String = "",
        var oldPassword: String = "",
        var password: String = "",
        var repeatPassword: String = "",
        var email: String = "",
        var firstName: String = "",
        var lastName: String = "",
        var phone: String = "",
        var addressName: String = "",
        var address: String = "",
        var city: String = "",
        var province: String = "",
        var postalCode: String = "",
        var country: String = "",
        var customerId: Long = 0,
        var token: String = ""
) {
    constructor(user: User) : this(
            id = user.id,
            username = user.userName,
            password = user.password,
            email = user.email,
            firstName = user.customer?.firstName!!,
            lastName = user.customer?.lastName!!,
            phone = user.customer?.phone!!,
            addressName = user.customer?.addressName!!,
            address = user.customer?.address!!,
            city = user.customer?.city!!,
            province = user.customer?.province!!,
            postalCode = user.customer?.postalCode!!,
            country = user.customer?.country!!,
            customerId = user.customer?.id!!
    )

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
                addressName = addressName,
                address = address,
                city = city,
                province = province,
                postalCode = postalCode,
                country = country,
        )
    }

}
