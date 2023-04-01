package com.technolearn.rasoulonlineshop.models.invoices

import com.technolearn.rasoulonlineshop.models.customers.User
import com.technolearn.rasoulonlineshop.models.enums.InvoiceStatus
import jakarta.persistence.*

@Entity
data class Invoice(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long = 0,
    var number: Int = 0,
    var status: InvoiceStatus = InvoiceStatus.NotPayed,
    var addDate: String = "",
    var paymentDate: String = "",

    @ManyToOne
    @JoinColumn(name = "user_id")
    var user: User? = null,

    @OneToMany(mappedBy = "invoice")
    var invoiceItems: Set<InvoiceItems>? = null,

    @OneToMany(mappedBy = "invoice")
    var transactions: Set<Transaction>? = null
)
