package com.technolearn.rasoulonlineshop.controllers.products

import com.technolearn.rasoulonlineshop.models.products.Product
import com.technolearn.rasoulonlineshop.services.products.ProductService
import com.technolearn.rasoulonlineshop.utils.NotFoundException
import com.technolearn.rasoulonlineshop.utils.ServiceResponse
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController


@RestController
@RequestMapping("api/product")
class ProductController {

    @Autowired
    private lateinit var service: ProductService

    //https://localhost:8080/api/product?pageIndex=1&pageSize=10
    @GetMapping("")
    fun getAll(@RequestParam pageIndex: Int, @RequestParam pageSize: Int): ServiceResponse<Product> {
        return try {
            ServiceResponse(service.getAll(pageIndex, pageSize), HttpStatus.OK.value())
        } catch (e: NotFoundException) {
            ServiceResponse(status = HttpStatus.INTERNAL_SERVER_ERROR.value(), message = "Not Found")
        } catch (e: Exception) {
            ServiceResponse(status = HttpStatus.INTERNAL_SERVER_ERROR.value(), message = "INTERNAL_SERVER_ERROR")
        }
    }

    @GetMapping("/new")
    fun getNewProduct(): ServiceResponse<Product> {
        return try {
            ServiceResponse(service.getNewProduct(), HttpStatus.OK.value())
        } catch (e: NotFoundException) {
            ServiceResponse(status = HttpStatus.INTERNAL_SERVER_ERROR.value(), message = "Not Found")
        } catch (e: Exception) {
            ServiceResponse(status = HttpStatus.INTERNAL_SERVER_ERROR.value(), message = "INTERNAL_SERVER_ERROR")
        }
    }

    @GetMapping("/popular")
    fun getPopularProduct(): ServiceResponse<Product> {
        return try {
            ServiceResponse(service.getPopularProduct(), HttpStatus.OK.value())
        } catch (e: NotFoundException) {
            ServiceResponse(status = HttpStatus.INTERNAL_SERVER_ERROR.value(), message = "Not Found")
        } catch (e: Exception) {
            ServiceResponse(status = HttpStatus.INTERNAL_SERVER_ERROR.value(), message = "INTERNAL_SERVER_ERROR")
        }
    }

    @GetMapping("/{id}")
    fun getById(@PathVariable id: Long): ServiceResponse<Product>? {

        return try {
            val data = service.getById(id) ?: throw NotFoundException("Not found")
            ServiceResponse(listOf(data), HttpStatus.OK.value())
        } catch (e: NotFoundException) {
            ServiceResponse(status = HttpStatus.NOT_FOUND.value(), message = e.message!!)
        } catch (e: Exception) {
            ServiceResponse(status = HttpStatus.INTERNAL_SERVER_ERROR.value(), message = e.message!!)
        }
    }

}