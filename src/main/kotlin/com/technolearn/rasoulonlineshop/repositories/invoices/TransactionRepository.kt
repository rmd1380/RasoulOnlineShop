package com.technolearn.rasoulonlineshop.repositories.invoices

import com.technolearn.rasoulonlineshop.models.invoices.Transaction
import org.springframework.data.repository.CrudRepository
import org.springframework.data.repository.PagingAndSortingRepository
import org.springframework.stereotype.Repository

@Repository
interface TransactionRepository : PagingAndSortingRepository<Transaction, Long>,CrudRepository<Transaction, Long> {

//    fun findByTransId(transId:String):Transaction?
}