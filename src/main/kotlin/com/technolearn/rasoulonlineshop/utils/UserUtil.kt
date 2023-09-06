package com.technolearn.rasoulonlineshop.utils

import com.technolearn.rasoulonlineshop.config.JwtTokenUtils
import jakarta.servlet.http.HttpServletRequest
import java.util.*

class UserUtil {
    companion object {
         fun getCurrentUser(jwtUtil: JwtTokenUtils, request: HttpServletRequest): String {
            val header = request.getHeader("Authorization")
            if (header == null || !header.lowercase(Locale.getDefault()).startsWith("bearer")) {
                throw JwtTokenException("Please set bearer token")
            }
            val token = header.substring(7)
            return jwtUtil.getEmailFromToken(token)
        }
    }
}