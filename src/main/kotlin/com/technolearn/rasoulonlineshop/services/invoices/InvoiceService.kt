package com.technolearn.rasoulonlineshop.services.invoices

import com.technolearn.rasoulonlineshop.models.enums.InvoiceStatus
import com.technolearn.rasoulonlineshop.models.invoices.Invoice
import com.technolearn.rasoulonlineshop.repositories.invoices.InvoiceRepository
import com.technolearn.rasoulonlineshop.services.customers.UserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Sort
import org.springframework.stereotype.Repository
import org.springframework.stereotype.Service
import java.util.Calendar

@Service
class InvoiceService {

    @Autowired
    lateinit var repository: InvoiceRepository

    @Autowired
    private lateinit var invoiceItemService: InvoiceItemsService

    @Autowired
    private lateinit var userService: UserService
    fun getById(id: Long, currentUser: String): Invoice? {
        val data = repository.findById(id)
        if (data.isEmpty) return null
        val user = userService.getUserByEmail(currentUser)
        if (user == null || user.id != data.get().user?.id) {
            throw Exception("You don't have permission to access this data")
        }
        return data.get()
    }

    fun getAllByUserId(userId: Long, pageIndex: Int, pageSize: Int, currentUser: String): List<Invoice> {
        val user = userService.getUserByEmail(currentUser)
        if (user == null || user.id != userId) {
            throw Exception("You don't have permission to access this data")
        }
        val pageRequest = PageRequest.of(pageIndex, pageSize, Sort.by("id"))
        return repository.findAllByUserId(userId, pageRequest).toList()
    }

    fun insert(data: Invoice, currentUser: String): Invoice {
        if (data.invoiceItems == null || data.invoiceItems!!.isEmpty())
            throw Exception("InvoiceItems are empty")
        if (data.user == null || data.user!!.id == null || data.user!!.id <= 0)
            throw Exception("Invalid user id")

        val user = userService.getUserByEmail(currentUser)
        if (user == null || user.id != data.id) {
            throw Exception("You don't have permission to access this data")
        }

        val date = Calendar.getInstance()
        data.status = InvoiceStatus.NotPayed
        data.addDate =
                "${date.get(Calendar.YEAR)}" +
                        "-${date.get(Calendar.MONTH)}" +
                        "-${date.get(Calendar.DAY_OF_MONTH)}" +
                        "-${date.get(Calendar.HOUR)}" +
                        "-${date.get(Calendar.MINUTE)}" +
                        "-${date.get(Calendar.SECOND)}"
        data.paymentDate = ""
        data.transactions = null
        data.invoiceItems!!.forEach {
            invoiceItemService.addItem(it)
        }
        return repository.save(data)
    }

    fun update(data: Invoice, currentUser: String): Invoice? {
        val oldData = getById(data.id, currentUser) ?: return null
        oldData.paymentDate = data.paymentDate
        oldData.status = data.status
        return repository.save(oldData)
    }

}