package com.technolearn.rasoulonlineshop.repositories.customers

import com.technolearn.rasoulonlineshop.models.customers.ShippingAddress
import org.springframework.data.repository.PagingAndSortingRepository
import org.springframework.stereotype.Repository

@Repository
interface ShippingAddressRepository : PagingAndSortingRepository<ShippingAddress, Long> {
}