package com.technolearn.rasoulonlineshop.vm

import com.technolearn.rasoulonlineshop.models.invoices.Invoice

data class VerifyResponseViewModel(
        val status:String,
        val ref_id:String,
        val invoice:Invoice,
        val code:Int
)