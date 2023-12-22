package com.technolearn.rasoulonlineshop.models.req

import com.technolearn.rasoulonlineshop.models.customers.User
import com.technolearn.rasoulonlineshop.models.invoices.InvoiceItems
import io.swagger.v3.oas.annotations.Hidden
import io.swagger.v3.oas.annotations.responses.ApiResponse
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id

data class InvoiceRequest(
        var user: User? = null,
        var invoiceItems: List<InvoiceItems>? = null,
)

