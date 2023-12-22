package com.technolearn.rasoulonlineshop.config.payment

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.context.annotation.Configuration

@Configuration
@ConfigurationProperties("payment.pay")
class PaymentConfig {

    var apikey: String = ""
    var send: String = ""
    var tokenEndPoint: String = ""
    var callbackUri: String = ""
    var paymentUri: String = ""
    var verifyUri: String = ""

}
