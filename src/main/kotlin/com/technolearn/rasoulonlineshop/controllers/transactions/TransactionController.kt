package com.technolearn.rasoulonlineshop.controllers.transactions

import com.technolearn.rasoulonlineshop.models.transaction.payment.SendReq
import com.technolearn.rasoulonlineshop.services.transactions.PaymentTransactionService
import com.technolearn.rasoulonlineshop.utils.ServiceResponse
import com.technolearn.rasoulonlineshop.vm.PaymentViewModel
import com.technolearn.rasoulonlineshop.vm.VerifyResponseViewModel
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("api/txn")
class TransactionController {

    @Autowired
    private lateinit var paymentTransactionService: PaymentTransactionService

    @PostMapping("/gotoPayment")
    fun gotoPayment(@RequestBody data: SendReq): ServiceResponse<String> {
        return try {
            ServiceResponse(paymentTransactionService.send(10000), HttpStatus.OK.value())
        } catch (e: Exception) {
            ServiceResponse(status = HttpStatus.INTERNAL_SERVER_ERROR.value(), message = e.message!!)
        }
    }

    @GetMapping("/sendPostRequest")
    fun sendPostRequest() {
        // Call your function here
        paymentTransactionService.send(10000)
    }


//    @GetMapping("/verify")
//    fun verify(
//            @RequestParam status:Int,
//            @RequestParam token:String,
//    ): ServiceResponse<VerifyResponseViewModel> {
//        return try {
//            ServiceResponse(paymentTransactionService.verifyPayment(status,token), HttpStatus.OK.value())
//        } catch (e: Exception) {
//            ServiceResponse(status = HttpStatus.INTERNAL_SERVER_ERROR.value(), message = e.message!!)
//        }
//    }
}