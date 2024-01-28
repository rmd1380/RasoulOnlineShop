package com.technolearn.rasoulonlineshop.models.invoices

import com.fasterxml.jackson.annotation.JsonIgnore
import com.technolearn.rasoulonlineshop.models.products.Product
import jakarta.persistence.*
import org.hibernate.annotations.ColumnDefault

@Entity
data class InvoiceItems(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long = 0,
    var quantity: Int = 0,
    var unitPrice: Double = 0.0,
    @ColumnDefault(value = "0")
    var finalPriceAfterDiscount: Int = 0,
    @ColumnDefault(value = "0")
    var finalPriceForPay: Int = 0,
    @ManyToOne
    @JoinColumn(name = "product_id")
    var product: Product? = null,

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "invoice_id")
    var invoice: Invoice? = null
)
