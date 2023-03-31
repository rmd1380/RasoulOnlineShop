package com.technolearn.rasoulonlineshop.models.customers

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id

//@Entity
data class ShippingAddress(
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    var id:Long=0,
    var name: String = "",
    var address: String = "",
    var city: String = "",
    var province: String = "",
    var postalCode: String = "",
    var country: String
)
