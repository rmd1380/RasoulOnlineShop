package com.technolearn.rasoulonlineshop.repositories.products

import com.technolearn.rasoulonlineshop.models.products.Product
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.CrudRepository
import org.springframework.data.repository.PagingAndSortingRepository
import org.springframework.stereotype.Repository
import org.springframework.data.domain.Pageable

@Repository
interface ProductRepository : PagingAndSortingRepository<Product, Long>, CrudRepository<Product, Long> {
    override fun findAll(): List<Product>
    fun findTop6ByOrderByAddDateDesc(): List<Product>

    fun findTop6ByOrderByRate(): List<Product>

    @Query("select price from Product where id = :id")
    fun findFirstPriceById(id: Long): Double?

    @Query("select hasDiscount from Product where id = :id")
    fun findFirstDiscountById(id: Long): Double?

    @Query("from Product where id in :idList")
    fun findAllByIdList(idList:List<Long>):List<Product>

    @Query("from Product where category.id =:categoryId")
    fun findAllByCategoryId(categoryId:Long):List<Product>
}