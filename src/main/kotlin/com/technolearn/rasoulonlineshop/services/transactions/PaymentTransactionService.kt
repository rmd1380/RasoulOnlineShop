package com.technolearn.rasoulonlineshop.services.transactions

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import com.technolearn.rasoulonlineshop.config.payment.PaymentConfig
import com.technolearn.rasoulonlineshop.models.customers.User
import com.technolearn.rasoulonlineshop.models.invoices.Invoice
import com.technolearn.rasoulonlineshop.models.invoices.Transaction
import com.technolearn.rasoulonlineshop.models.products.Product
import com.technolearn.rasoulonlineshop.models.transaction.payment.*
import com.technolearn.rasoulonlineshop.services.customers.UserService
import com.technolearn.rasoulonlineshop.services.invoices.InvoiceService
import com.technolearn.rasoulonlineshop.services.invoices.TransactionService
import com.technolearn.rasoulonlineshop.services.products.ProductService
import com.technolearn.rasoulonlineshop.utils.exceptions.NotFoundException
import com.technolearn.rasoulonlineshop.vm.PaymentViewModel
import com.technolearn.rasoulonlineshop.utils.payment.PaymentStatusCodeUtil
import com.technolearn.rasoulonlineshop.vm.VerifyResponseViewModel
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpEntity
import org.springframework.http.HttpHeaders
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service
import org.springframework.web.client.ResourceAccessException
import org.springframework.web.client.RestTemplate

@Service
class PaymentTransactionService {

    @Autowired
    private lateinit var configuration: PaymentConfig

    @Autowired
    private lateinit var txnService: TransactionService

    @Autowired
    private lateinit var userService: UserService

    @Autowired
    private lateinit var invoiceService: InvoiceService

    @Autowired
    private lateinit var productService: ProductService

    @Autowired
    private lateinit var transactionService: TransactionService

    fun sendPaymentRequest(paymentRequest: SendReq): String {
        val restTemplate = RestTemplate()

        val headers = HttpHeaders()
        headers.contentType = MediaType.APPLICATION_JSON

        val requestEntity = HttpEntity(paymentRequest, headers)

        val url = "https://pay.ir/pg/send"
        return restTemplate.postForObject(url, requestEntity, String::class.java) ?: ""
    }
//    fun getPaymentUri(data: PaymentViewModel): String {
//        /*
//            1) check user exists?
//            2) if(!userExists) then register user
//            3) create invoice
//            4) get payment url
//            5) create txn
//         */
//        checkValidation(data)
//        var user: User? = registerUser(data)
//        if (user == null) {
//
//            user = userService.getById(data.user.id)
//            userService.update(data.user.convertToUser(), user!!.email)
//        }
//
//        val currentUser = user.email
//        val invoice = createInvoice(user, data, currentUser)
//        val productList = getProduct(data)
//        val amount: Double = getTotalAmount(data, productList)
////        val response = preparePaymentUri(data, amount, invoice)
//        val response = send(amount.toInt())
//
//        val txn = Transaction(
//                status = response.status,
//                token = response.token,
//                invoice = invoice,
//        )
//        txnService.insert(txn)
//        if (response.status != -1) {
//            throw Exception(PaymentStatusCodeUtil.getMessage(response.status))
//        }
//        return "${configuration.callbackUri}${response.token}"
//    }

//    fun verifyPayment(status: Int, token: String): VerifyResponseViewModel {
//        val txn = transactionService.getByTransId(order_id) ?: throw NotFoundException("Transaction Not Found")
//        val response = verify(txn)
//
//        txn.status = response.status
////        txn.custom = NextPayStatusCodeUtil.getMessage(response.code)
//
//        if (txn.status == 1) {
//            txn.refId = response.Shaparak_Ref_Id
//        }
//        transactionService.update(txn)
//        return VerifyResponseViewModel(
//                status = PaymentStatusCodeUtil.getMessage(response.status),
//                ref_id = txn.refId,
//                invoice = txn.invoice!!,
//                code = txn.status
//
//        )
//    }

//    private fun doVerifyPayment(trans_id: String, txn: Transaction): VerifyRes {
//        val mapper = jacksonObjectMapper()
//        val url = configuration.verifyUri
//        val restClient = RestTemplate()
//        val headers = HttpHeaders().apply {
//            contentType = MediaType.APPLICATION_JSON
//        }
//
//        val request = VerifyReq(
//                api_key = configuration.apikey,
//                trans_id = trans_id,
//                amount = txn.amount
//        )
//        val resp = restClient.postForEntity(url, HttpEntity(request, headers), String::class.java)
//        if (resp.statusCode.is2xxSuccessful) {
//            return mapper.readValue(resp.body!!)
//        } else {
//            throw IllegalStateException()
//        }
//    }

    //region helper functions
    private fun getTotalAmount(data: PaymentViewModel, productList: List<Product>): Double {
        var amount = 0.0
        data.invoiceItems.forEach { item ->
            val product = productList.first { x -> x.id == item.product!!.id }
            amount += product.price * item.quantity
        }
        return amount
    }

    private fun getProduct(data: PaymentViewModel): List<Product> {
        val idList = ArrayList<Long>()
        data.invoiceItems.forEach {
            idList.add(it.product!!.id)
        }
        return productService.getAllByIdList(idList)
    }

    private fun createInvoice(user: User?, data: PaymentViewModel, currentUser: String): Invoice {
        val invoice = Invoice(
                user = user,
                invoiceItems = data.invoiceItems
        )
        invoiceService.insert(invoice, currentUser)
        return invoice
    }

    private fun registerUser(data: PaymentViewModel): User? {
        return if (data.user.id <= 0) {
            userService.insert(data.user.convertToUser())
        } else {
            val isUserExist = userService.isUserExist(data.user.id)
            if (isUserExist) {
                null
            } else {
                throw Exception("User Not Exist")
            }
        }
    }

    private fun checkValidation(data: PaymentViewModel) {
        if (data.user == null || data.invoiceItems == null) {
            throw Exception("Invalid Input Data")
        }
    }

//    private fun preparePaymentUri(data: PaymentViewModel, amount: Double, invoice: Invoice): TokenRes {
//        val mapper = jacksonObjectMapper()
//        val url = configuration.tokenEndPoint
//        val restClient = RestTemplate()
//        val headers = HttpHeaders().apply {
//            contentType = MediaType.APPLICATION_JSON
//        }
//
//        val request = TokenReq(
//                api_key = configuration.apikey,
//                order_id = invoice.id.toString(),
//                amount = amount.toInt(),
//                callback_uri = configuration.callbackUri
//        )
//        val resp = restClient.postForEntity(url, HttpEntity(request, headers), String::class.java)
//        if (resp.statusCode.is2xxSuccessful) {
//            return mapper.readValue<TokenRes>(resp.body!!)
//        } else {
//            throw IllegalStateException()
//        }
//    }
    //endregion

    fun send(amount: Int): String {
        val url = "https://pay.ir/pg/send"

        val headers = HttpHeaders()
        headers.contentType = MediaType.APPLICATION_JSON

        val requestBody = """
            {
                "api": "test",
                "amount": 100000,
                "redirect": "https://localhost:8080/api/txn/verify",
                "mobile": "",
                "factorNumber": "",
                "description": "",
                "validCardNumber": ""
            }
        """.trimIndent()

        val entity = HttpEntity(requestBody, headers)

        val restTemplate = RestTemplate()

        val response: ResponseEntity<String> = restTemplate.postForEntity(url, entity, String::class.java)
        try {

            // Handle the response
            println("Response: ${response.statusCode}")
            println("Body: ${response.body}")
        } catch (ex: ResourceAccessException) {
            // Log or handle the exception
            println("Error sending request: ${ex.message}")
            ex.printStackTrace()
        }
        return response.body?:""
//        val mapper = jacksonObjectMapper()
//        val url = configuration.send
//        val restClient = RestTemplate()
//        val headers = HttpHeaders().apply {
//            contentType = MediaType.APPLICATION_JSON
//        }
//        val request = SendReq(
//                api = "test",
//                amount = amount,
//                redirect = configuration.callbackUri,
//                mobile = "",
//                factorNumber = "",
//                description = ""
//        )
//        val responseBody = restClient.postForObject(url, HttpEntity(request, headers), String::class.java)
//        if (responseBody != null) {
//            println("responseBody:::${mapper.readValue<SendRes>(responseBody)}")
//            return mapper.readValue(responseBody)
//        } else {
//            throw IllegalStateException("Response body is null")
//        }
////        val resp = restClient.postForEntity(url, HttpEntity(request, headers), String::class.java)
////        if (resp.statusCode.is2xxSuccessful) {
////            println("responseBody:::${mapper.readValue<SendRes>(resp.body ?: "")}")
////            return mapper.readValue<SendRes>(resp.body!!)
////        } else {
////            throw IllegalStateException()
////        }
    }
    private fun verify(txn: Transaction): VerifyRes {
        val mapper = jacksonObjectMapper()
        val url = configuration.verifyUri
        val restClient = RestTemplate()
        val headers = HttpHeaders().apply {
            contentType = MediaType.APPLICATION_JSON
        }
        val request = VerifyReq(
                api = "test",
                token = txn.token,
        )
        val resp = restClient.postForEntity(url, HttpEntity(request, headers), String::class.java)
        if (resp.statusCode.is2xxSuccessful) {
            return mapper.readValue(resp.body!!)
        } else {
            throw IllegalStateException()
        }
    }
//    fun verify(api: String, token: String): String {
//        val params = JSONObject()
//        params.put("api", api)
//        params.put("token", token)
//
//        return curlPost("https://pay.ir/pg/verify", params.toString())
//    }
//
//    private fun curlPost(url: String, params: String): String {
//        val connection = URL(url).openConnection() as HttpURLConnection
//        connection.doOutput = true
//        connection.requestMethod = "POST"
//        connection.setRequestProperty("Content-Type", "application/json")
//
//        val outputStream = connection.outputStream
//        outputStream.write(params.toByteArray())
//        outputStream.flush()
//        outputStream.close()
//
//        val inputStream = connection.inputStream
//        val response = inputStream.bufferedReader().readText()
//        inputStream.close()
//
//        return response
//    }
}