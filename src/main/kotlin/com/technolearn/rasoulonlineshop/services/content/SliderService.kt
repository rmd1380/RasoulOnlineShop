package com.technolearn.rasoulonlineshop.services.content

import com.technolearn.rasoulonlineshop.models.content.Slider
import com.technolearn.rasoulonlineshop.repositories.content.SliderRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class SliderService {

    @Autowired
    lateinit var repository: SliderRepository

    //CRUD
    fun getAll(): List<Slider> {
        return repository.findAll()
    }

    fun getById(id: Long): Slider? {
        val data = repository.findById(id)
        if (data.isEmpty) return null
        return data.get()
    }

    fun getAllCount(): Long {
        return repository.count()
    }
}