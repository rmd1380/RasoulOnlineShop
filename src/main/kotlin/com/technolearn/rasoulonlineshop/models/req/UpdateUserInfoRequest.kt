package com.technolearn.rasoulonlineshop.models.req

import com.technolearn.rasoulonlineshop.models.customers.User
import io.swagger.v3.oas.annotations.Hidden
import io.swagger.v3.oas.annotations.responses.ApiResponse
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id

data class UpdateUserInfoRequest(
        var id: Long = 0,
        var firstName: String = "",
        var lastName: String = "",
        var phone: String = "",
        var addressName: String = "",
        var address: String = "",
        var city: String = "",
        var province: String = "",
        var postalCode: String = "",
        var country: String = "",
)

