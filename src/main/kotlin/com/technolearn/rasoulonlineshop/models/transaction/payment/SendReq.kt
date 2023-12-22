package com.technolearn.rasoulonlineshop.models.transaction.payment

data class SendReq(
        val api: String,
        val amount: Int,
        val redirect: String,
        val mobile: String,
        val factorNumber: String,
        val description: String
)
