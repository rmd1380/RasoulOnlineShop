package com.technolearn.rasoulonlineshop.services.products

import com.technolearn.rasoulonlineshop.models.products.Product
import com.technolearn.rasoulonlineshop.repositories.products.ProductRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Sort
import org.springframework.stereotype.Service

@Service
class ProductService {

    @Autowired
    lateinit var repository: ProductRepository

//    fun getAll(): List<Product> {
//        return repository.findAll()
//    }

    fun getAll(pageIndex: Int, pageSize: Int): List<Product> {
        var pageRequest = PageRequest.of(pageIndex, pageSize, Sort.by("id"))
        return repository.findAll(pageRequest).toList()
    }

    fun getById(id: Long): Product? {
        val data = repository.findById(id)
        if (data.isEmpty) return null
        return data.get()
    }

    fun getNewProduct(): List<Product> {
        return repository.findTop6ByOrderByAddDateDesc()
    }

    fun getPopularProduct(): List<Product> {
        return repository.findTop6ByOrderByRate()
    }

    fun getAllCount(): Long {
        return repository.count()
    }

    fun getPriceById(id: Long): Double? {
        return repository.findFirstPriceById(id)
    }
}