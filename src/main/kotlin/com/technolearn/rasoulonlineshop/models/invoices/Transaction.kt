package com.technolearn.rasoulonlineshop.models.invoices

import com.fasterxml.jackson.annotation.JsonIgnore
import jakarta.persistence.*

@Entity
data class Transaction(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long = 0,
    var transId: String = "",
    var code: Int = 0,
    var refId: String = "",
    var orderId: String = "",
    var cardHolder: String = "",
    var customerPhone: String = "",
    var ShaparakRefId	: String = "",
    var custom	: String = "",
    var refundRequest	: String = "",
    var amount: Int = 0,

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "invoice_id")
    var invoice: Invoice? = null
)
