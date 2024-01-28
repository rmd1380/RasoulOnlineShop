package com.technolearn.rasoulonlineshop.services.invoices

import com.technolearn.rasoulonlineshop.models.invoices.InvoiceItems
import com.technolearn.rasoulonlineshop.repositories.invoices.InvoiceItemsRepository
import com.technolearn.rasoulonlineshop.services.products.ProductService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import kotlin.math.roundToInt

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

    fun getAllByInvoiceId(invoiceId: Long): Set<InvoiceItems> {
        return repository.findAllByInvoiceId(invoiceId)
    }

    fun addItem(invoiceItem: InvoiceItems): InvoiceItems {
        if (invoiceItem.quantity <= 0)
            throw Exception("Invalid Quantity")
        if (invoiceItem.product?.id == null ||
            invoiceItem.product!!.id <= 0
        )
            throw Exception("Invalid Product")

        val productPrice = productService.getPriceById(invoiceItem.product!!.id)
        val productDiscount = productService.getDiscountById(invoiceItem.product!!.id)
        val discountAmount = (productPrice?.times(productDiscount ?: 0.0))?.div(100.0)
        val finalPrice = productPrice?.minus(discountAmount ?: 0.0)
        invoiceItem.unitPrice = productPrice ?: 0.0
        invoiceItem.finalPriceAfterDiscount = finalPrice?.roundToInt() ?: 0
        invoiceItem.finalPriceForPay = finalPrice?.roundToInt()?.times(invoiceItem.quantity) ?: 0
        return repository.save(invoiceItem)
    }
}