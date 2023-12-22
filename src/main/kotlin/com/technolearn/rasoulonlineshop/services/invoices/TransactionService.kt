package com.technolearn.rasoulonlineshop.services.invoices

import com.technolearn.rasoulonlineshop.models.invoices.Transaction
import com.technolearn.rasoulonlineshop.repositories.invoices.TransactionRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class TransactionService {

    @Autowired
    private lateinit var repository: TransactionRepository

    fun insert(data: Transaction): Transaction {
        return repository.save(data)
    }

    fun getById(id: Long): Transaction? {
        val data = repository.findById(id)
        if (data.isEmpty) return null
        return data.get()
    }

//    fun getByTransId(transId: String): Transaction? {
//        return repository.findByTransId(transId)
//    }
//
//    fun update(data: Transaction): Transaction? {
//        val oldData = getById(data.id) ?: return null
//        oldData.status = data.status
//        oldData.token = data.token
//        return repository.save(oldData)
//    }

}