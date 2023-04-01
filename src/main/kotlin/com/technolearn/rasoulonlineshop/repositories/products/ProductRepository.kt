package com.technolearn.rasoulonlineshop.repositories.products

import com.technolearn.rasoulonlineshop.models.products.Product
import org.springframework.data.repository.CrudRepository
import org.springframework.data.repository.PagingAndSortingRepository
import org.springframework.stereotype.Repository

@Repository
interface ProductRepository : PagingAndSortingRepository<Product, Long>, CrudRepository<Product, Long> {
    override fun findAll(): List<Product>
    fun top6ProductByAddDate(): List<Product>
    fun top6ProductByRate(): List<Product>
}