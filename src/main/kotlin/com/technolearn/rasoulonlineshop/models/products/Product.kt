package com.technolearn.rasoulonlineshop.models.products

import com.fasterxml.jackson.annotation.JsonIgnore
import com.technolearn.rasoulonlineshop.models.invoices.InvoiceItems
import jakarta.persistence.*

@Entity
data class Product(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long = 0,
    var brand: String = "",
    var title: String = "",
    var image: ArrayList<String> = arrayListOf(),
    var threeDModel: String? = "",
    var addDate: Long = 0,
    var price: Double = 0.0,
    var rate: Double = 0.0,
    var description: String = "",
    var hasDiscount: Double? = 0.0,

    @ManyToOne
    @JoinColumn(name = "category_id")
    var category: Category? = null,

    @ManyToMany
    var colors: Set<Color>? = null,

    @ManyToMany
    var sizes: Set<Size>? = null,

    @JsonIgnore
    @OneToMany(mappedBy = "product")
    var invoiceItems: Set<InvoiceItems>? = null
)
