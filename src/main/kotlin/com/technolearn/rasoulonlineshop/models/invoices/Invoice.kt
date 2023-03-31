package com.technolearn.rasoulonlineshop.models.invoices

import com.technolearn.rasoulonlineshop.models.customers.User
import com.technolearn.rasoulonlineshop.models.enums.InvoiceStatus
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id

@Entity
data class Invoice(
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    var id:Long=0,
    var number: Int = 0,
    //var status: InvoiceStatus = InvoiceStatus.NotPayed,
    var addDate: String = "",
    var paymentDate: String = "",
    var user: User? = null
)
