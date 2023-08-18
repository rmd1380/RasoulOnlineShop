package com.technolearn.rasoulonlineshop.repositories.products

import com.technolearn.rasoulonlineshop.models.products.Color
import com.technolearn.rasoulonlineshop.models.products.Size
import org.springframework.data.repository.CrudRepository
import org.springframework.data.repository.PagingAndSortingRepository
import org.springframework.stereotype.Repository

@Repository
interface SizeRepository : PagingAndSortingRepository<Size,Long>,CrudRepository<Size, Long> {
    override fun findAll(): List<Size>
}