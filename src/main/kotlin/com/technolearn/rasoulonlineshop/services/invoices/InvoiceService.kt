package com.technolearn.rasoulonlineshop.services.invoices

import com.technolearn.rasoulonlineshop.models.enums.InvoiceStatus
import com.technolearn.rasoulonlineshop.models.invoices.Invoice
import com.technolearn.rasoulonlineshop.models.invoices.InvoiceItems
import com.technolearn.rasoulonlineshop.repositories.invoices.InvoiceItemsRepository
import com.technolearn.rasoulonlineshop.repositories.invoices.InvoiceRepository
import com.technolearn.rasoulonlineshop.services.customers.UserService
import com.technolearn.rasoulonlineshop.services.products.ProductService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Sort
import org.springframework.stereotype.Repository
import org.springframework.stereotype.Service
import java.util.*
import kotlin.jvm.optionals.toSet
import kotlin.math.roundToInt

@Service
class InvoiceService {

    @Autowired
    lateinit var repository: InvoiceRepository

//    @Autowired
//    private lateinit var invoiceItemService: InvoiceItemsService

    @Autowired
    private lateinit var userService: UserService

    @Autowired
    lateinit var invoiceItemRepository: InvoiceItemsRepository

    @Autowired
    private lateinit var productService: ProductService
    fun getById(id: Long, currentUser: String): Invoice? {
        val data = repository.findById(id)
        val invoiceItems=invoiceItemRepository.findAllByInvoiceId(id)
        if (data.isEmpty) return null
        val user = userService.getUserByEmail(currentUser)
        if (user == null || user.id != data.get().user?.id) {
            throw Exception("You don't have permission to access this data")
        }
        val finalData=data.get()
        finalData.invoiceItems=invoiceItems
        return finalData
    }

    fun getAllByUserId(userId: Long, currentUser: String): List<Invoice> {
        val user = userService.getUserByEmail(currentUser)
        if (user == null || user.id != userId) {
            throw Exception("You don't have permission to access this data")
        }
        return repository.findAllByUserId(userId).toList()
    }

    fun insert(data: Invoice, status: InvoiceStatus, currentUser: String): Invoice {
        if (data.invoiceItems == null || data.invoiceItems!!.isEmpty())
            throw Exception("InvoiceItems are empty")
        if (data.user == null || data.user!!.id <= 0)
            throw Exception("Invalid user id")

        val user = userService.getUserByEmail(currentUser)
        if (user == null || user.id != data.user!!.id) {
            throw Exception("You don't have permission to access this data")
        }

        val date = Calendar.getInstance(Locale.US)
        data.status = status
        data.addDate =
            "${date.get(Calendar.YEAR)}" +
                    "-${date.get(Calendar.MONTH)}" +
                    "-${date.get(Calendar.DAY_OF_MONTH)}"
        data.paymentDate = ""
//        data.transactions = null
        data.invoiceItems!!.forEach {
            addItem(it,data)
        }
        return repository.save(data)
    }

    fun update(data: Invoice, currentUser: String): Invoice? {
        val oldData = getById(data.id, currentUser) ?: return null
        oldData.paymentDate = data.paymentDate
        oldData.status = data.status
        return repository.save(oldData)
    }

    fun addItem(invoiceItem: InvoiceItems,invoice: Invoice): InvoiceItems {
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
        return invoiceItemRepository.save(invoiceItem)
    }

}