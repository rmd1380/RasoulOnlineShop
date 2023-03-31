package com.technolearn.rasoulonlineshop.repositories.products

import com.technolearn.rasoulonlineshop.models.products.Color
import org.springframework.data.repository.PagingAndSortingRepository
import org.springframework.stereotype.Repository

@Repository
interface ColorRepository : PagingAndSortingRepository<Color, Long> {
}