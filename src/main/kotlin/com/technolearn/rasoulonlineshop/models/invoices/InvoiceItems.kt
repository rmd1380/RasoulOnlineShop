package com.technolearn.rasoulonlineshop.models.invoices

import com.technolearn.rasoulonlineshop.models.products.Product
import jakarta.persistence.*

@Entity
data class InvoiceItems(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long = 0,
    var quantity: Int = 0,
    var unitPrice: Double = 0.0,

    @ManyToOne
    @JoinColumn(name = "product_id")
    var product: Product? = null,

    @ManyToOne
    @JoinColumn(name = "invoice_id")
    var invoice: Invoice? = null
)
