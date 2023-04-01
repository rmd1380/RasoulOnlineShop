package com.technolearn.rasoulonlineshop.repositories.customers

import com.technolearn.rasoulonlineshop.models.customers.ShippingAddress
import org.springframework.data.repository.CrudRepository
import org.springframework.data.repository.PagingAndSortingRepository
import org.springframework.stereotype.Repository

@Repository
interface ShippingAddressRepository : PagingAndSortingRepository<ShippingAddress,Long>,CrudRepository<ShippingAddress,Long> {
    override fun findAll(): List<ShippingAddress>
}