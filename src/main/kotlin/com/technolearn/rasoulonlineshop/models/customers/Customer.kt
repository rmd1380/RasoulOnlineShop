package com.technolearn.rasoulonlineshop.models.customers

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id

@Entity
data class Customer(
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    var id:Long=0,
    var firstName: String = "",
    var lastName: String = "",
    //var shippingAddress: ShippingAddress? = null,
    var phone: Int = 0
)
