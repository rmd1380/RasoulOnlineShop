package com.technolearn.rasoulonlineshop.models.products

import com.technolearn.rasoulonlineshop.models.invoices.InvoiceItems
import jakarta.persistence.*

@Entity
data class Product(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long = 0,
    var brand: String = "",
    var title: String = "",
    var image:String="",
    var addDate: String = "",
    var price: Double = 0.0,
    var rate: Double = 0.0,
    var label: String = "",
    var description: String = "",

    @ManyToOne
    @JoinColumn(name = "category_id")
    var category: Category? = null,

    @ManyToMany
    var colors: Set<Color>? = null,

    @ManyToMany
    var sizes: Set<Size>? = null,

    @OneToMany(mappedBy = "product")
    var invoiceItems: Set<InvoiceItems>? = null
)
