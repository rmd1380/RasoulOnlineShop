package com.technolearn.rasoulonlineshop.services.customers

import com.technolearn.rasoulonlineshop.models.customers.Customer
import com.technolearn.rasoulonlineshop.repositories.customers.CustomerRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class CustomerService {

    @Autowired
    private lateinit var repository: CustomerRepository


    //CRUD
    fun insert(data: Customer): Customer {
        if (data.firstName.isEmpty()) {
            throw Exception("Please Enter FirstName")
        }
        if (data.lastName.isEmpty()) {
            throw Exception("Please Enter LastName")
        }
        if (data.phone.isEmpty()) {
            throw Exception("Please Enter PhoneNumber")
        }
        val phoneExists = repository.existsByPhone(data.phone)
        if (phoneExists) {
            throw Exception("Phone number already exists")
        }
        return repository.save(data)

    }

    fun getById(id: Long): Customer? {
        val data = repository.findById(id)
        if (data.isEmpty) return null
        return data.get()
    }

    fun update(data: Customer): Customer? {
        val oldData = getById(data.id) ?: return null
        oldData.firstName = data.firstName
        oldData.lastName = data.lastName
        oldData.phone = data.phone
        oldData.addressName = data.addressName
        oldData.address = data.address
        oldData.city = data.city
        oldData.province = data.province
        oldData.postalCode = data.postalCode
        oldData.country = data.country

        return repository.save(data)

    }
}