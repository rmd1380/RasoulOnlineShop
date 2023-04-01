package com.technolearn.rasoulonlineshop.services.customers

import com.technolearn.rasoulonlineshop.models.customers.User
import com.technolearn.rasoulonlineshop.repositories.customers.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service


@Service
class UserService {

    @Autowired
    lateinit var repository: UserRepository

    //CRUD
    fun insert(data: User): User {
        if (data.userName.isEmpty()) {
            throw Exception("Please Enter UserName")
        }
        if (data.email.isEmpty()) {
            throw Exception("Please Enter Email")
        }
        if (data.password.isEmpty()) {
            throw Exception("Please Enter Password")
        }
        return repository.save(data)
    }

    fun getUserByEmailAndPassword(email: String, password: String): User? {
        return repository.findFirstByEmailAndPassWord(email, password)
    }

    fun getById(id: Long): User? {
        val data = repository.findById(id)
        if (data.isEmpty) return null
        return data.get()
    }

    fun update(data: User): User? {
        val oldData = getById(data.id) ?: return null
        oldData.email = data.email
        oldData.password = data.password
        return repository.save(oldData)
    }

}