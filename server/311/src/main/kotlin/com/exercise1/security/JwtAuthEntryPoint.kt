package com.exercise1.security

import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse
 
import org.slf4j.LoggerFactory
import org.springframework.security.core.AuthenticationException
import org.springframework.security.web.AuthenticationEntryPoint
import org.springframework.stereotype.Component
 
@Component
class JwtAuthEntryPoint: AuthenticationEntryPoint {
 
    private val logger = LoggerFactory.getLogger(JwtAuthEntryPoint::class.java)
    
    override fun commence(request: HttpServletRequest, response: HttpServletResponse, authException: AuthenticationException) {
        logger.error("Unauthorized error. Message - {}", authException.message)
        response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Error -> Unauthorized")
    }
}
