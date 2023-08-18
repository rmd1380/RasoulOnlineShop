package com.technolearn.rasoulonlineshop.services.invoices

import com.technolearn.rasoulonlineshop.models.invoices.InvoiceItems
import com.technolearn.rasoulonlineshop.repositories.invoices.InvoiceItemsRepository
import com.technolearn.rasoulonlineshop.services.products.ProductService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class InvoiceItemsService {

    @Autowired
    lateinit var repository: InvoiceItemsRepository

    @Autowired
    private lateinit var productService: ProductService
    fun getById(id: Long): InvoiceItems? {
        val data = repository.findById(id)
        if (data.isEmpty) return null
        return data.get()
    }

    fun getAllByInvoiceId(invoiceId: Int): List<InvoiceItems> {
        return repository.findAllByUserId(invoiceId)
    }

    fun addItem(invoiceItem: InvoiceItems): InvoiceItems {
        if (invoiceItem.quantity <= 0)
            throw Exception("Invalid Quantity")
        if (invoiceItem.product?.id == null ||
            invoiceItem.product!!.id <= 0
        )
            throw Exception("Invalid Product")

        val productPrice = productService.getPriceById(invoiceItem.product!!.id)
        invoiceItem.unitPrice = productPrice ?: 0.0
        return repository.save(invoiceItem)
    }
}