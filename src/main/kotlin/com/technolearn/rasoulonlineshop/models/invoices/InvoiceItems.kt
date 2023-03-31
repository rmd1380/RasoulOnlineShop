package com.technolearn.rasoulonlineshop.models.invoices

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id

@Entity
data class InvoiceItems(
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    var id:Long=0,
//    var product: Product? = null,
    var quantity: Int = 0,
    var unitPrice: Double = 0.0,
//    var invoice: Invoice? = null
)
