package com.technolearn.rasoulonlineshop.repositories.invoices

import com.technolearn.rasoulonlineshop.models.invoices.Invoice
import org.springframework.data.repository.PagingAndSortingRepository
import org.springframework.stereotype.Repository

@Repository
interface InvoiceRepository : PagingAndSortingRepository<Invoice, Long> {
}