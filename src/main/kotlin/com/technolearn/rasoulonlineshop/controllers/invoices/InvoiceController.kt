package com.technolearn.rasoulonlineshop.controllers.invoices

import com.technolearn.rasoulonlineshop.config.JwtTokenUtils
import com.technolearn.rasoulonlineshop.models.enums.InvoiceStatus
import com.technolearn.rasoulonlineshop.models.invoices.Invoice
import com.technolearn.rasoulonlineshop.models.req.InvoiceRequest
import com.technolearn.rasoulonlineshop.repositories.invoices.InvoiceItemsRepository
import com.technolearn.rasoulonlineshop.services.invoices.InvoiceService
import com.technolearn.rasoulonlineshop.services.products.ProductService
import com.technolearn.rasoulonlineshop.utils.ServiceResponse
import com.technolearn.rasoulonlineshop.utils.UserUtil.Companion.getCurrentUser
import com.technolearn.rasoulonlineshop.utils.exceptions.NotFoundException
import jakarta.servlet.http.HttpServletRequest
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*


@RestController
@RequestMapping("api/invoice")
class InvoiceController {

    @Autowired
    private lateinit var service: InvoiceService

    @Autowired
    private lateinit var jwtUtil: JwtTokenUtils

    //http://localhost:8080/api/Invoice
    @GetMapping("/{id}")
    fun getById(@PathVariable id: Long,request: HttpServletRequest): ServiceResponse<Invoice> {

        return try {
            val currentUser = getCurrentUser(jwtUtil, request)
            val data = service.getById(id,currentUser) ?: throw NotFoundException("Not found")
            ServiceResponse(data, HttpStatus.OK.value())
        } catch (e: NotFoundException) {
            ServiceResponse(status = HttpStatus.NOT_FOUND.value(), message = e.message!!)
        } catch (e: Exception) {
            ServiceResponse(status = HttpStatus.INTERNAL_SERVER_ERROR.value(), message = e.message!!)
        }
    }


    @GetMapping("/user/{userId}")
    fun getAllByUserId(
        @PathVariable userId: Long,
        request: HttpServletRequest
    ): ServiceResponse<List<Invoice>>? {
        return try {
            val currentUser = getCurrentUser(jwtUtil, request)
            val data = service.getAllByUserId(userId, currentUser)
            ServiceResponse(data, HttpStatus.OK.value())
        } catch (e: NotFoundException) {
            ServiceResponse(status = HttpStatus.NOT_FOUND.value(), message = e.message!!)
        } catch (e: Exception) {
            ServiceResponse(status = HttpStatus.INTERNAL_SERVER_ERROR.value(), message = e.message!!)
        }
    }

    @PostMapping("/{status}")
    fun addInvoice(
        @PathVariable status: Int,
        @RequestBody invoice: InvoiceRequest,
        request: HttpServletRequest
    ): ServiceResponse<Invoice> {
        return try {
            val finalInvoice = Invoice(
                user = invoice.user,
                invoiceItems = invoice.invoiceItems
            )
            val finalStatus:InvoiceStatus= when (status) {
                0 -> InvoiceStatus.Cancelled
                1 -> InvoiceStatus.Processing
                2 -> InvoiceStatus.Delivered
                else -> {throw Exception("Enter Valid Payment Status")}
            }
            val currentUser = getCurrentUser(jwtUtil, request)
            val data = service.insert(finalInvoice,finalStatus, currentUser)
            ServiceResponse(data, HttpStatus.OK.value())
        } catch (e: NotFoundException) {
            ServiceResponse(status = HttpStatus.NOT_FOUND.value(), message = e.message!!)
        } catch (e: Exception) {
            ServiceResponse(status = HttpStatus.INTERNAL_SERVER_ERROR.value(), message = e.message!!)
        }
    }
}