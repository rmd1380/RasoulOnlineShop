package com.technolearn.rasoulonlineshop.models.invoices

import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import com.technolearn.rasoulonlineshop.models.invoices.Invoice
import jakarta.persistence.Entity

@Entity
data class Transaction(
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    var id:Long=0,
    var authority: String = "",
    var status: Int = 0,
    var refId: String = "",
//    var invoice: Invoice? = null
)
