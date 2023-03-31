package com.technolearn.rasoulonlineshop.repositories.customers

import com.technolearn.rasoulonlineshop.models.customers.User
import org.springframework.data.repository.PagingAndSortingRepository
import org.springframework.stereotype.Repository

@Repository
interface UserRepository : PagingAndSortingRepository<User, Long> {
}