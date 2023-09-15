package com.technolearn.rasoulonlineshop.services.customers

import com.technolearn.rasoulonlineshop.models.customers.Customer
import com.technolearn.rasoulonlineshop.models.customers.User
import com.technolearn.rasoulonlineshop.repositories.customers.UserRepository
import com.technolearn.rasoulonlineshop.utils.SecurityUtil
import jakarta.validation.Valid
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.validation.annotation.Validated


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
        if(!SecurityUtil.isEmailValid(data.email)){
            throw Exception("Invalid Email Format")
        }
        if (data.password.isEmpty()) {
            throw Exception("Please Enter Password")
        }
        if (data.password.length < 8) {
            throw Exception("Password length should be at least 8 character")
        }
        val isEmailExists = repository.existsByEmail(data.email)
        if (isEmailExists) {
            throw Exception("Email already exists")
        }
        val isUserNameExists = repository.existsByUserName(data.userName)
        if (isUserNameExists) {
            throw Exception("UserName already exists")
        }
        data.password = SecurityUtil.encryptSHA256(data.password)
        customerService.insert(data.customer!!)
        val saveData = repository.save(data)
        saveData.password = ""
        return saveData
    }

    fun getUserByEmailAndPassword(email: String, password: String): User? {
        if (email.isEmpty() || password.isEmpty()) {
            throw Exception("Please Enter Email And Password")
        }
        val pass = SecurityUtil.encryptSHA256(password)
        return repository.findFirstByEmailAndPassword(email, pass)
    }

    fun getUserByEmail(email: String): User? {
        if (email.isEmpty()) {
            throw Exception("Please Enter Email")
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
        if (data.password.length < 8) {
            throw Exception("Password length should be at least 8 character")
        }
        if (user.password != SecurityUtil.encryptSHA256(oldPassword))
            throw Exception("Invalid current password")
        if (user.password == data.password) {
            throw Exception("Your new password is same as current password")
        }
        user.password = SecurityUtil.encryptSHA256(data.password)
        val savedData = repository.save(user)
        savedData.password = ""
        return savedData
    }

}