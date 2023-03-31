package com.technolearn.rasoulonlineshop.models.products

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id

@Entity
data class Color(
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    var id:Long=0,
    var title: String = "",
    var hexValue: String = ""
)
