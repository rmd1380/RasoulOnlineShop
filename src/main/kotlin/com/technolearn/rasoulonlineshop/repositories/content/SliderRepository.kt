package com.technolearn.rasoulonlineshop.repositories.content

import com.technolearn.rasoulonlineshop.models.content.Slider
import org.springframework.data.repository.CrudRepository
import org.springframework.data.repository.PagingAndSortingRepository
import org.springframework.stereotype.Repository

@Repository
interface SliderRepository : PagingAndSortingRepository<Slider, Long>,CrudRepository<Slider,Long> {
    override fun findAll(): List<Slider>


}