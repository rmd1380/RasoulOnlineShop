package com.technolearn.rasoulonlineshop.models.content

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id

@Entity
data class Slider(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id:Long=0,
    var image: String = "",
    var title: String = "",
    var link: String = ""
)
