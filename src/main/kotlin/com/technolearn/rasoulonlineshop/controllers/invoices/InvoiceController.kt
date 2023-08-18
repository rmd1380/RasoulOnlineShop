package com.technolearn.rasoulonlineshop.controllers.invoices

import com.technolearn.rasoulonlineshop.models.invoices.Invoice
import com.technolearn.rasoulonlineshop.services.invoices.InvoiceService
import com.technolearn.rasoulonlineshop.utils.NotFoundException
import com.technolearn.rasoulonlineshop.utils.ServiceResponse
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*


@RestController
@RequestMapping("api/invoice")
class InvoiceController {

    @Autowired
    private lateinit var service: InvoiceService

    //http://localhost:8080/api/Invoice
    @GetMapping("/{id}")
    fun getById(@PathVariable id: Long): ServiceResponse<Invoice> {

        return try {
            val data = service.getById(id) ?: throw NotFoundException("Not found")
            ServiceResponse(listOf(data), HttpStatus.OK.value())
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
    ): ServiceResponse<Invoice>? {

        return try {
            val data = service.getAllByUserId(userId, pageIndex, pageSize) ?: throw NotFoundException("Not found")
            ServiceResponse(data, HttpStatus.OK.value())
        } catch (e: NotFoundException) {
            ServiceResponse(status = HttpStatus.NOT_FOUND.value(), message = e.message!!)
        } catch (e: Exception) {
            ServiceResponse(status = HttpStatus.INTERNAL_SERVER_ERROR.value(), message = e.message!!)
        }
    }

    @PostMapping("")
    fun addInvoice(@RequestBody invoice: Invoice): ServiceResponse<Invoice> {

        return try {
            val data = service.insert(invoice) ?: throw NotFoundException("Not found")
            ServiceResponse(listOf(data), HttpStatus.OK.value())
        } catch (e: NotFoundException) {
            ServiceResponse(status = HttpStatus.NOT_FOUND.value(), message = e.message!!)
        } catch (e: Exception) {
            ServiceResponse(status = HttpStatus.INTERNAL_SERVER_ERROR.value(), message = e.message!!)
        }
    }

    @PutMapping("")
    fun editInvoice(@RequestBody invoice: Invoice): ServiceResponse<Invoice> {

        return try {
            val data = service.update(invoice) ?: throw NotFoundException("Not found")
            ServiceResponse(listOf(data), HttpStatus.OK.value())
        } catch (e: NotFoundException) {
            ServiceResponse(status = HttpStatus.NOT_FOUND.value(), message = e.message!!)
        } catch (e: Exception) {
            ServiceResponse(status = HttpStatus.INTERNAL_SERVER_ERROR.value(), message = e.message!!)
        }
    }
}