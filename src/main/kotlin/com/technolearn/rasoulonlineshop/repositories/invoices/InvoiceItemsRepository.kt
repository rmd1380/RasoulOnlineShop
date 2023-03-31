package com.technolearn.rasoulonlineshop.repositories.invoices

import com.technolearn.rasoulonlineshop.models.invoices.InvoiceItems
import org.springframework.data.repository.PagingAndSortingRepository
import org.springframework.stereotype.Repository

@Repository
interface InvoiceItemsRepository : PagingAndSortingRepository<InvoiceItems, Long> {
}