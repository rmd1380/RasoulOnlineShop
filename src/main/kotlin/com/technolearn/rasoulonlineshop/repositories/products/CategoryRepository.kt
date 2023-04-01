package com.technolearn.rasoulonlineshop.repositories.products

import com.technolearn.rasoulonlineshop.models.products.Category
import org.springframework.data.repository.CrudRepository
import org.springframework.data.repository.PagingAndSortingRepository
import org.springframework.stereotype.Repository

@Repository
interface CategoryRepository : PagingAndSortingRepository<Category, Long>, CrudRepository<Category, Long> {
    override fun findAll(): List<Category>
}