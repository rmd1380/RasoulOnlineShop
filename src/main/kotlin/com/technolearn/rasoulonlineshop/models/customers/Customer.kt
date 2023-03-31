package com.technolearn.rasoulonlineshop.models.customers

import jakarta.persistence.*

@Entity
data class Customer(
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    var id:Long=0,
    var firstName: String = "",
    var lastName: String = "",
    var phone: Int = 0,

    @OneToOne(mappedBy = "customer")
    var user:User?=null,

    @OneToMany(mappedBy = "customer")
    var shippingAddresses: Set<ShippingAddress>? = null,
)
