package com.technolearn.rasoulonlineshop.services.customers

import com.technolearn.rasoulonlineshop.models.customers.Customer
import com.technolearn.rasoulonlineshop.models.customers.User
import com.technolearn.rasoulonlineshop.repositories.customers.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service


@Service
class UserService {

    @Autowired
    private lateinit var repository: UserRepository

    @Autowired
    lateinit var customerService: CustomerService

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
        val emailExists = repository.existsByEmail(data.email)
        if (emailExists) {
            throw Exception("Email already exists")
        }
        val userNameExists = repository.existsByUserName(data.userName)
        if (userNameExists) {
            throw Exception("UserName already exists")
        }
        customerService.insert(data.customer ?: Customer())
        return repository.save(data)
    }

    fun getUserByEmailAndPassword(email: String, password: String): User? {
        if (email.isEmpty() || password.isEmpty()) {
            throw Exception("Please Enter Email And Password")
        }
        return repository.findFirstByEmailAndPassword(email, password)
    }

    fun getUserByEmail(email: String): User? {
        if (email.isEmpty()) {
            throw Exception("Please Enter Email And Password")
        }
        return repository.findFirstByEmail(email)
    }

    fun getById(id: Long): User? {
        val data = repository.findById(id)
        if (data.isEmpty) return null
        return data.get()
    }

    fun update(data: User, currentUser: String): User? {
        val user = repository.findFirstByEmail(currentUser)
        if (user == null || user.id != data.id) {
            throw Exception("You don't have permission to update info")
        }

        val oldCustomer = customerService.getById(data.customer!!.id) ?: return null
        oldCustomer.firstName = data.customer!!.firstName
        oldCustomer.lastName = data.customer!!.lastName
        oldCustomer.phone = data.customer!!.phone
        oldCustomer.addressName = data.customer!!.addressName
        oldCustomer.address = data.customer!!.address
        oldCustomer.city = data.customer!!.city
        oldCustomer.province = data.customer!!.province
        oldCustomer.postalCode = data.customer!!.postalCode
        oldCustomer.country = data.customer!!.country
        customerService.update(oldCustomer)
        data.password = ""
        return data
    }

    fun changePassword(data: User, oldPassword: String, repeatPassword: String, currentUser: String): User? {
        val user = repository.findFirstByEmail(currentUser)
        if (user == null || user.id != data.id) {
            throw Exception("You don't have permission to change password")

        }
        if (data.password != repeatPassword)
            throw Exception("Password not matched to repeat password")
        //TODO: check password strength
        val oldData = getById(data.id) ?: return null
        if (oldData.password != oldPassword)
            throw Exception("Invalid current password")
        if (oldData.password == data.password) {
            throw Exception("Your new password is same as current password")
        }
        oldData.password = data.password
        val savedData = repository.save(oldData)
        savedData.password = ""
        return savedData
    }

}