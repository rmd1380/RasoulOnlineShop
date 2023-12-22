package com.technolearn.rasoulonlineshop.vm

import com.technolearn.rasoulonlineshop.models.invoices.InvoiceItems

data class PaymentViewModel(
        val user: UserViewModel,
        val invoiceItems: List<InvoiceItems>
)