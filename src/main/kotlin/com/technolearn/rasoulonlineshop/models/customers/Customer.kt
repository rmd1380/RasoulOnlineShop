package com.technolearn.rasoulonlineshop.models.customers

import com.fasterxml.jackson.annotation.JsonIgnore
import jakarta.persistence.*

@Entity
data class Customer(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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

    @JsonIgnore
    @OneToOne(mappedBy = "customer")
    var user: User? = null,
)
