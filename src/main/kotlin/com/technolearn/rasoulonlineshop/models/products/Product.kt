package com.technolearn.rasoulonlineshop.models.products

import com.technolearn.rasoulonlineshop.models.invoices.InvoiceItems
import jakarta.persistence.*

@Entity
data class Product(
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    var id: Long = 0,
    var brand: String = "",
    var title: String = "",
    var price: Double = 0.0,
    var rate: Double = 0.0,
    var label: String = "",
    var size: String = "",
    var description: String = "",

    @ManyToOne
    @JoinColumn(name = "category_id")
    var category: Category? = null,

    @ManyToMany
    var colors: Set<Color>? = null,

    @OneToMany(mappedBy = "product")
    var invoiceItems: Set<InvoiceItems>? = null
)
