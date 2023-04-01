package com.technolearn.rasoulonlineshop.models.customers

import jakarta.persistence.*

@Entity
data class ShippingAddress(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long = 0,
    var name: String = "",
    var address: String = "",
    var city: String = "",
    var province: String = "",
    var postalCode: String = "",
    var country: String = "",

    @ManyToOne
    @JoinColumn(name = "customer_id")
    var customer: Customer? = null


)
