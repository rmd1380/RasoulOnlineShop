package com.technolearn.rasoulonlineshop.models.invoices

import jakarta.persistence.*

@Entity
data class Transaction(
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    var id: Long = 0,
    var authority: String = "",
    var status: Int = 0,
    var refId: String = "",

    @ManyToOne
    @JoinColumn(name = "invoice_id")
    var invoice: Invoice? = null
)
