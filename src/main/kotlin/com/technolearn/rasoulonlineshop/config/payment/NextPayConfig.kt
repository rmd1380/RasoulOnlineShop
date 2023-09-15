package com.technolearn.rasoulonlineshop.config.payment

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.context.annotation.Configuration

@Configuration
@ConfigurationProperties("payment.nextpay")
class NextPayConfig {

      var apikey: String? = null

      var tokenEndPoint: String? = null

      var callbackUri: String? = null

      var paymentUri: String? = null

      var verifyUri: String? = null

}
