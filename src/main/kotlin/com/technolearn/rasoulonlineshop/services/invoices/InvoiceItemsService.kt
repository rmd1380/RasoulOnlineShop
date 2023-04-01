package com.technolearn.rasoulonlineshop.services.invoices

import com.technolearn.rasoulonlineshop.models.invoices.Invoice
import com.technolearn.rasoulonlineshop.models.invoices.InvoiceItems
import com.technolearn.rasoulonlineshop.repositories.invoices.InvoiceItemsRepository
import com.technolearn.rasoulonlineshop.repositories.invoices.InvoiceRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Sort
import org.springframework.stereotype.Repository
import org.springframework.stereotype.Service

@Service
class InvoiceItemsService {

    @Autowired
    lateinit var repository: InvoiceItemsRepository

    fun getById(id: Long): InvoiceItems? {
        val data = repository.findById(id)
        if (data.isEmpty) return null
        return data.get()
    }

    fun getAllByInvoiceId(invoiceId: Int): List<InvoiceItems> {
        return repository.findAllByUserId(invoiceId)
    }
}