package com.technolearn.rasoulonlineshop.services.products

import com.technolearn.rasoulonlineshop.models.products.Category
import com.technolearn.rasoulonlineshop.repositories.products.CategoryRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class CategoryService {

    @Autowired
    lateinit var repository: CategoryRepository

    fun getAll(): List<Category> {
        return repository.findAll()
    }

    fun getById(id: Long): Category? {
        val data = repository.findById(id)
        if (data.isEmpty) return null
        return data.get()
    }

    fun getAllCount(): Long {
        return repository.count()
    }
}