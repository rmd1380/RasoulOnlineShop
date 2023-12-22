package com.technolearn.rasoulonlineshop.models.transaction.payment

data class VerifyRes(
        val status: Int,
        val amount: String,
        val transId: String,
        val factorNumber: String,
        val mobile: String,
        val description: String,
        val cardNumber: String,
        val message: String,
)
