package com.exercise1.security

import javax.servlet.FilterChain
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

import org.slf4j.LoggerFactory
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource
import org.springframework.web.filter.OncePerRequestFilter
import java.lang.Exception

class JwtAuthTokenFilter(
  private val tokenProvider: JwtProvider,
  private val userDetailsService: UserDetailsService
) : OncePerRequestFilter() {

  private val logger = LoggerFactory.getLogger(JwtAuthTokenFilter::class.java)

  override fun doFilterInternal(request: HttpServletRequest, response: HttpServletResponse, filterChain: FilterChain) {
    try {
      logger.info(request)
      val jwt = getJwt(request)
      if (jwt != null && tokenProvider.validateJwtToken(jwt)) {
        val username = tokenProvider.getUserNameFromJwtToken(jwt)

        val userDetails = userDetailsService.loadUserByUsername(username)
        val authentication = UsernamePasswordAuthenticationToken(
          userDetails,
          null,
          userDetails.authorities
        )
        authentication.details = WebAuthenticationDetailsSource().buildDetails(request)
        SecurityContextHolder.getContext().authentication = authentication
      }
    } catch (e: Exception) {
      logger.error("Can NOT set user authentication -> Message: {}", e)
    }

    filterChain.doFilter(request, response)
  }

  private fun getJwt(request: HttpServletRequest): String? {
    val authHeader = request.getHeader("Authorization")

    if (authHeader != null && authHeader.startsWith("Bearer ")) {
      return authHeader.replace("Bearer ", "")
    }

    return null
  }
}
