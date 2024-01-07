package com.technolearn.rasoulonlineshop.config

import com.technolearn.rasoulonlineshop.vm.UserViewModel
import io.jsonwebtoken.Claims
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component
import java.io.Serializable
import java.util.*
import java.util.function.Function

@Component
class JwtTokenUtils : Serializable {
    private final val JWT_TOKEN_VALIDITY_IN_DAYS = 100
    val JWT_TOKEN_VALIDITY = JWT_TOKEN_VALIDITY_IN_DAYS * (24 * 60 * 60).toLong()


    @Value("\${jwt.secret}")
    private val secret: String? = null

    //retrieve username from jwt token
    fun getEmailFromToken(token: String): String {
        return getClaimFromToken(token) { it.subject }
    }

    //retrieve expiration date from jwt token
    fun getExpirationDateFromToken(token: String): Date {
        return getClaimFromToken(token) { it.expiration }
    }

    fun <T> getClaimFromToken(token: String, claimsResolver: Function<Claims, T>): T {
        val claims = getAllClaimsFromToken(token)
        return claimsResolver.apply(claims)
    }

    //for retrieving any information from token we will need the secret key
    private fun getAllClaimsFromToken(token: String?): Claims {
        return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).body
    }

    //check if the token has expired
    private fun isTokenExpired(token: String): Boolean {
        val expiration = getExpirationDateFromToken(token)
        return expiration.before(Date())
    }

    //generate token for user
    fun generateToken(userDetail: UserViewModel): String? {
        val claims: Map<String, Any> = HashMap()
        return doGenerateToken(claims, userDetail.email)
    }

    //while creating the token -
    //1. Define  claims of the token, like Issuer, Expiration, Subject, and the ID
    //2. Sign the JWT using the HS512 algorithm and secret key.
    //3. According to JWS Compact Serialization(https://tools.ietf.org/html/draft-ietf-jose-json-web-signature-41#section-3.1)
    //   compaction of the JWT to a URL-safe string
    private fun doGenerateToken(claims: Map<String, Any>, subject: String): String? {
        return Jwts.builder().setClaims(claims).setSubject(subject).setIssuedAt(Date(System.currentTimeMillis()))
            .setExpiration(Date(System.currentTimeMillis() + JWT_TOKEN_VALIDITY * 1000))
            .signWith(SignatureAlgorithm.HS512, secret).compact()
    }

    //validate token
    fun validateToken(token: String, userDetail: UserViewModel): Boolean {
        val email = getEmailFromToken(token)
        return email == userDetail.email && !isTokenExpired(token)
    }
}