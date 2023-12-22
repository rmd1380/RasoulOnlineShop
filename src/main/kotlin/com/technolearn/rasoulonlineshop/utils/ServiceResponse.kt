package com.technolearn.rasoulonlineshop.utils

import java.io.Serializable

class ServiceResponse<T>(
    var data: T? = null,
    var status: Int,
    var message: String = "",
    var totalCount: Long = 0
) : Serializable