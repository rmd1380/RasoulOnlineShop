package com.technolearn.rasoulonlineshop.repositories.customers

import com.technolearn.rasoulonlineshop.models.customers.Customer
import org.springframework.data.repository.CrudRepository
import org.springframework.data.repository.PagingAndSortingRepository
import org.springframework.stereotype.Repository

@Repository
interface CustomerRepository : PagingAndSortingRepository<Customer, Long>,CrudRepository<Customer, Long> {
}