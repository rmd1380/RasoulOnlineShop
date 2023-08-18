package com.technolearn.rasoulonlineshop.services.products

import com.technolearn.rasoulonlineshop.models.products.Color
import com.technolearn.rasoulonlineshop.models.products.Size
import com.technolearn.rasoulonlineshop.repositories.products.ColorRepository
import com.technolearn.rasoulonlineshop.repositories.products.SizeRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class SizeService {

    @Autowired
    lateinit var repository: SizeRepository

    fun getAll(): List<Size> {
        return repository.findAll()
    }

    fun getById(id: Long): Size? {
        val data = repository.findById(id)
        if (data.isEmpty) return null
        return data.get()
    }

    fun getAllCount(): Long {
        return repository.count()
    }
}