package com.technolearn.rasoulonlineshop.utils

import com.google.common.hash.Hashing

class SecurityUtil {

    companion object {
        private const val EMAIL_REGEX = "^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$"

        fun encryptSHA256(plainText: String): String {
            val hashFunction = Hashing.sha256()
            val hc = hashFunction
                    .newHasher()
                    .putString(plainText, Charsets.UTF_8)
                    .hash()
            return hc.toString()
        }
        fun isEmailValid(email: String): Boolean {
            return EMAIL_REGEX.toRegex().matches(email)
        }
    }
}