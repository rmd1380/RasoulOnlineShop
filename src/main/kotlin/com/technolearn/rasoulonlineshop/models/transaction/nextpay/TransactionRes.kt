package com.technolearn.rasoulonlineshop.models.transaction.nextpay

data class TransactionRes(
        val code: Int,
        val trans_id: String,
        val amount: Int
)