package com.technolearn.rasoulonlineshop.controllers.products

import com.technolearn.rasoulonlineshop.models.products.Category
import com.technolearn.rasoulonlineshop.services.products.CategoryService
import com.technolearn.rasoulonlineshop.utils.NotFoundException
import com.technolearn.rasoulonlineshop.utils.ServiceResponse
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController


@RestController
@RequestMapping("api/category")
class CategoryController {

    @Autowired
    private lateinit var service: CategoryService

    //http://localhost:8080/api/category
    @GetMapping("")
    fun getAll(): ServiceResponse<Category> {
        return try {
            ServiceResponse(service.getAll(), HttpStatus.OK.value())
        } catch (e: NotFoundException) {
            ServiceResponse(status = HttpStatus.INTERNAL_SERVER_ERROR.value(), message = "Not Found")
        } catch (e: Exception) {
            ServiceResponse(status = HttpStatus.INTERNAL_SERVER_ERROR.value(), message = "INTERNAL_SERVER_ERROR")
        }
    }

    @GetMapping("/{id}")
    fun getById(@PathVariable id: Long): ServiceResponse<Category>? {

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