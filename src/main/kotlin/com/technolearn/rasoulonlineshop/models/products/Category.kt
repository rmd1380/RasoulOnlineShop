package com.technolearn.rasoulonlineshop.models.products

import com.fasterxml.jackson.annotation.JsonIgnore
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.OneToMany

@Entity
data class Category(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long = 0,
    var title: String = "",
    var image:String="",

    @JsonIgnore
    @OneToMany(mappedBy = "category")
    var products: Set<Product>? = null
)
