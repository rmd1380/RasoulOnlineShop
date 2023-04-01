package com.technolearn.rasoulonlineshop.services.products

import com.technolearn.rasoulonlineshop.models.products.Color
import com.technolearn.rasoulonlineshop.repositories.products.ColorRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class ColorService {

    @Autowired
    lateinit var repository: ColorRepository

    fun getAll(): List<Color> {
        return repository.findAll()
    }

    fun getById(id: Long): Color? {
        val data = repository.findById(id)
        if (data.isEmpty) return null
        return data.get()
    }

    fun getAllCount(): Long {
        return repository.count()
    }
}