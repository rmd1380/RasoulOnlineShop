package com.technolearn.rasoulonlineshop.services.customers

import com.technolearn.rasoulonlineshop.models.customers.ShippingAddress
import com.technolearn.rasoulonlineshop.repositories.customers.ShippingAddressRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Sort
import org.springframework.stereotype.Service


@Service
class ShippingAddressService {

    @Autowired
    lateinit var repository: ShippingAddressRepository

    //CRUD
    fun insert(data: ShippingAddress): ShippingAddress {
        return repository.save(data)
    }

    fun getAll(): List<ShippingAddress> {
        return repository.findAll()
    }

    fun getAll(pageIndex: Int, pageSize: Int): List<ShippingAddress> {
        val pageRequest = PageRequest.of(pageIndex, pageSize, Sort.by("id"))
        return repository.findAll(pageRequest).toList()
    }

    fun getById(id: Long): ShippingAddress? {
        val data = repository.findById(id)
        if (data.isEmpty) return null
        return data.get()
    }

    fun update(data: ShippingAddress): ShippingAddress? {
        val oldData = getById(data.id) ?: return null
        oldData.name = data.name
        oldData.address = data.address
        oldData.city = data.city
        oldData.province = data.province
        oldData.postalCode = data.postalCode
        oldData.country = data.country
        return repository.save(oldData)
    }

    fun delete(data: ShippingAddress): Boolean {
        repository.delete(data)
        return true
    }

    fun getAllCount(): Long {
        return repository.count()
    }
}