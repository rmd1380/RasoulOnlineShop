package com.technolearn.rasoulonlineshop.models.invoices

import com.fasterxml.jackson.annotation.JsonIgnore
import jakarta.persistence.*

@Entity
data class Transaction(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long = 0,
    var status: Int = 0,
    var token: String = "",


    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "invoice_id")
    var invoice: Invoice? = null
)
