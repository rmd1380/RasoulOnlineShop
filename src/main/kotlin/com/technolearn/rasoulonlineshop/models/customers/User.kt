package com.technolearn.rasoulonlineshop.models.customers

import com.technolearn.rasoulonlineshop.models.customers.Customer
import jakarta.persistence.*

@Entity
@Table(name="users")
data class User(
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    var id:Long=0,
    var userName: String = "",
    var password: String = "",
    //var customer: Customer? = null
)
