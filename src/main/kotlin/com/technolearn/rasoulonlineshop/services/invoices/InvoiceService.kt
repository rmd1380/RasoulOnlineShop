package com.technolearn.rasoulonlineshop.services.invoices

import com.technolearn.rasoulonlineshop.models.invoices.Invoice
import com.technolearn.rasoulonlineshop.repositories.invoices.InvoiceRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Sort
import org.springframework.stereotype.Repository
import org.springframework.stereotype.Service

@Service
class InvoiceService {

    @Autowired
    lateinit var repository: InvoiceRepository

    fun getById(id: Long): Invoice? {
        val data = repository.findById(id)
        if (data.isEmpty) return null
        return data.get()
    }

    fun getAllByUserId(userId: Int, pageIndex: Int, pageSize: Int): List<Invoice> {
        val pageRequest = PageRequest.of(pageIndex, pageSize, Sort.by("id"))
        return repository.findAllByUserId(userId, pageRequest).toList()
    }

    private fun insert(data: Invoice): Invoice {
        return repository.save(data)
    }

    private fun update(data: Invoice): Invoice? {
        val oldData = getById(data.id) ?: return null
        oldData.paymentDate = data.paymentDate
        oldData.status = data.status
        return repository.save(oldData)
    }

}