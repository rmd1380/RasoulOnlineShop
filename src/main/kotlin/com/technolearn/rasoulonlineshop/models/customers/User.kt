package com.technolearn.rasoulonlineshop.models.customers

import com.fasterxml.jackson.annotation.JsonIgnore
import com.technolearn.rasoulonlineshop.models.invoices.Invoice
import jakarta.persistence.*
import jakarta.validation.constraints.Email

@Entity
@Table(name = "users")
data class User(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long = 0,
    var userName: String = "",
    var password: String = "",
    var email: String = "",

    @OneToOne
    @JoinColumn(name = "customer_id")
    var customer: Customer? = null,

    @JsonIgnore
    @OneToMany(mappedBy = "user")
    var invoices: Set<Invoice>? = null
)
