package com.technolearn.rasoulonlineshop.controllers.customers

import com.technolearn.rasoulonlineshop.config.JwtTokenUtils
import com.technolearn.rasoulonlineshop.models.customers.User
import com.technolearn.rasoulonlineshop.models.req.CreateUserRequest
import com.technolearn.rasoulonlineshop.models.req.LoginUserRequest
import com.technolearn.rasoulonlineshop.models.req.UpdateUserInfoRequest
import com.technolearn.rasoulonlineshop.services.customers.UserService
import com.technolearn.rasoulonlineshop.utils.NotFoundException
import com.technolearn.rasoulonlineshop.utils.ServiceResponse
import com.technolearn.rasoulonlineshop.utils.UserUtil.Companion.getCurrentUser
import com.technolearn.rasoulonlineshop.vm.UserViewModel
import jakarta.servlet.http.HttpServletRequest
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*


@RestController
@RequestMapping("api/user")
class UserController {

    @Autowired
    private lateinit var service: UserService


    @Autowired
    private lateinit var jwtUtil: JwtTokenUtils
    //https://localhost:8080/api/User
    //https://localhost:8080/swagger-ui/index.html

    @PostMapping("/login")
    fun login(@RequestBody request: LoginUserRequest): ServiceResponse<UserViewModel> {
        return try {
            val data = service.getUserByEmailAndPassword(request.email, request.password)
                    ?: throw NotFoundException("Not found")
            val vm = UserViewModel(data)
            vm.token = jwtUtil.generateToken(vm)!!
            ServiceResponse(listOf(vm), HttpStatus.OK.value())
        } catch (e: NotFoundException) {
            ServiceResponse(status = HttpStatus.NOT_FOUND.value(), message = e.message!!)
        } catch (e: Exception) {
            ServiceResponse(status = HttpStatus.INTERNAL_SERVER_ERROR.value(), message = e.message!!)
        }
    }


    @GetMapping("")
    fun getUser(request: HttpServletRequest): ServiceResponse<User> {
        return try {
            val currentUser = getCurrentUser(jwtUtil, request)
            val data = service.getUserByEmail(currentUser) ?: throw NotFoundException("Not found")
            ServiceResponse(listOf(data), HttpStatus.OK.value())
        } catch (e: NotFoundException) {
            ServiceResponse(status = HttpStatus.NOT_FOUND.value(), message = e.message!!)
        } catch (e: Exception) {
            ServiceResponse(status = HttpStatus.INTERNAL_SERVER_ERROR.value(), message = e.message!!)
        }
    }

    @PostMapping("/register")
    fun addUser(@RequestBody request: CreateUserRequest): ServiceResponse<User> {
        return try {
            // Create a User object with the required fields
            val user = UserViewModel(
                    username = request.username,
                    password = request.password,
                    email = request.email
            )
            val data = service.insert(user.convertToUser()) ?: throw NotFoundException("Not found")
            ServiceResponse(listOf(data), HttpStatus.OK.value())
        } catch (e: NotFoundException) {
            ServiceResponse(status = HttpStatus.NOT_FOUND.value(), message = e.message!!)
        } catch (e: Exception) {
            ServiceResponse(status = HttpStatus.INTERNAL_SERVER_ERROR.value(), message = e.message!!)
        }
    }


    @PutMapping("/update")
    fun editUser(@RequestBody userReq: UpdateUserInfoRequest, request: HttpServletRequest): ServiceResponse<User> {
        return try {
            val currentUser = getCurrentUser(jwtUtil, request)
            val user = UserViewModel(
                    id = userReq.id,
                    firstName = userReq.firstName,
                    lastName = userReq.lastName,
                    phone = userReq.phone,
                    addressName = userReq.addressName,
                    address = userReq.address,
                    city = userReq.city,
                    province = userReq.province,
                    postalCode = userReq.postalCode,
                    country = userReq.country,
                    customerId = userReq.id
            )
            val data = service.update(user.convertToUser(), currentUser) ?: throw NotFoundException("Not found")
            ServiceResponse(listOf(data), HttpStatus.OK.value())
        } catch (e: NotFoundException) {
            ServiceResponse(status = HttpStatus.NOT_FOUND.value(), message = e.message!!)
        } catch (e: Exception) {
            ServiceResponse(status = HttpStatus.INTERNAL_SERVER_ERROR.value(), message = e.message!!)
        }
    }


    @PutMapping("/changePassword")
    fun changePassword(@RequestBody user: UserViewModel, request: HttpServletRequest): ServiceResponse<User> {
        return try {
            val currentUser = getCurrentUser(jwtUtil, request)
            val data = service.changePassword(user.convertToUser(), user.oldPassword, user.repeatPassword, currentUser)
                    ?: throw NotFoundException("Not found")
            ServiceResponse(listOf(data), HttpStatus.OK.value())
        } catch (e: NotFoundException) {
            ServiceResponse(status = HttpStatus.NOT_FOUND.value(), message = e.message!!)
        } catch (e: Exception) {
            ServiceResponse(status = HttpStatus.INTERNAL_SERVER_ERROR.value(), message = e.message!!)
        }
    }
}