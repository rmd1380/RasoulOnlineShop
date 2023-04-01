package com.technolearn.rasoulonlineshop.services.invoices

import com.technolearn.rasoulonlineshop.models.invoices.Transaction
import com.technolearn.rasoulonlineshop.repositories.invoices.TransactionRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class TransactionService {

    @Autowired
    lateinit var repository: TransactionRepository

    private fun insert(data: Transaction): Transaction {
        return repository.save(data)
    }

    fun getById(id: Long): Transaction? {
        val data = repository.findById(id)
        if (data.isEmpty) return null
        return data.get()
    }

    private fun update(data: Transaction): Transaction? {
        val oldData = getById(data.id) ?: return null
        oldData.refId = data.refId
        oldData.status = data.status
        return repository.save(oldData)
    }

}