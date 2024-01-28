package com.technolearn.rasoulonlineshop.repositories.invoices

import com.technolearn.rasoulonlineshop.models.invoices.InvoiceItems
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.CrudRepository
import org.springframework.data.repository.PagingAndSortingRepository
import org.springframework.stereotype.Repository

@Repository
interface InvoiceItemsRepository : PagingAndSortingRepository<InvoiceItems, Long>,CrudRepository<InvoiceItems, Long> {

    @Query("from InvoiceItems where invoice.id = :invoiceId")
    fun findAllByInvoiceId(invoiceId: Long): Set<InvoiceItems>
}