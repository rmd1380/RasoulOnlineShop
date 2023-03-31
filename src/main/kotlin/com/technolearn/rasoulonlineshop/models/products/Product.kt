package com.technolearn.rasoulonlineshop.models.products

import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import com.technolearn.rasoulonlineshop.models.products.Category
import com.technolearn.rasoulonlineshop.models.products.Color
import jakarta.persistence.Entity

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
//    var category: Category? = null,
//    var color: Color? = null
)
