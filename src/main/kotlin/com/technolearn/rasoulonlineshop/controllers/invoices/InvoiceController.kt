package com.technolearn.rasoulonlineshop.controllers.invoices

import com.technolearn.rasoulonlineshop.config.JwtTokenUtils
import com.technolearn.rasoulonlineshop.models.invoices.Invoice
import com.technolearn.rasoulonlineshop.models.req.InvoiceRequest
import com.technolearn.rasoulonlineshop.services.invoices.InvoiceService
import com.technolearn.rasoulonlineshop.utils.exceptions.NotFoundException
import com.technolearn.rasoulonlineshop.utils.ServiceResponse
import com.technolearn.rasoulonlineshop.utils.UserUtil.Companion.getCurrentUser
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
            @RequestParam pageSize: Int,
            @RequestParam pageIndex: Int,
            request: HttpServletRequest
    ): ServiceResponse<List<Invoice>>? {

        return try {
            val currentUser = getCurrentUser(jwtUtil, request)
            val data = service.getAllByUserId(userId, pageIndex, pageSize, currentUser)
            ServiceResponse(data, HttpStatus.OK.value())
        } catch (e: NotFoundException) {
            ServiceResponse(status = HttpStatus.NOT_FOUND.value(), message = e.message!!)
        } catch (e: Exception) {
            ServiceResponse(status = HttpStatus.INTERNAL_SERVER_ERROR.value(), message = e.message!!)
        }
    }

    @PostMapping("")
    fun addInvoice(
            @RequestBody invoice: InvoiceRequest,
            request: HttpServletRequest): ServiceResponse<Invoice> {
        return try {
            val finalInvoice=Invoice(
                    user = invoice.user,
                    invoiceItems = invoice.invoiceItems
            )
            val currentUser = getCurrentUser(jwtUtil, request)
            val data = service.insert(finalInvoice, currentUser)
            ServiceResponse(data, HttpStatus.OK.value())
        } catch (e: NotFoundException) {
            ServiceResponse(status = HttpStatus.NOT_FOUND.value(), message = e.message!!)
        } catch (e: Exception) {
            ServiceResponse(status = HttpStatus.INTERNAL_SERVER_ERROR.value(), message = e.message!!)
        }
    }
}