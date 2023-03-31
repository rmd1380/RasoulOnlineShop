package com.technolearn.rasoulonlineshop.repositories.products

import com.technolearn.rasoulonlineshop.models.products.Product
import org.springframework.data.repository.PagingAndSortingRepository
import org.springframework.stereotype.Repository

@Repository
interface ProductRepository : PagingAndSortingRepository<Product, Long> {
}