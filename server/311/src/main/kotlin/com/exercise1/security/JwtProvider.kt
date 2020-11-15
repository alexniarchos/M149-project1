package com.exercise1.security

import io.jsonwebtoken.*
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Value
import org.springframework.security.core.Authentication
import org.springframework.stereotype.Component


import java.util.Date

@Component
class JwtProvider {

  private val logger = LoggerFactory.getLogger(JwtProvider::class.java)

  @Value("\${security.jwtSecret}")
  private lateinit var jwtSecret: String

  @Value("\${security.jwtExpiration}")
  private var jwtExpiration: Int = 10000

    fun generateJwtToken(authentication: Authentication): String {

    val userPrincipal = authentication.principal as UserPrinciple

    return Jwts.builder()
      .setSubject(userPrincipal.username)
      .setIssuedAt(Date())
      .setExpiration(Date(Date().time + jwtExpiration))
      .signWith(SignatureAlgorithm.HS512, jwtSecret)
      .compact()
  }

  fun validateJwtToken(authToken: String): Boolean {
    try {
      Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(authToken)
      return true
    } catch (e: SignatureException) {
      logger.error("Invalid JWT signature -> Message: {0} ", e)
    } catch (e: MalformedJwtException) {
      logger.error("Invalid JWT token -> Message: {0}", e)
    } catch (e: ExpiredJwtException) {
      logger.error("Expired JWT token -> Message: {0}", e)
    } catch (e: UnsupportedJwtException) {
      logger.error("Unsupported JWT token -> Message: {0}", e)
    } catch (e: IllegalArgumentException) {
      logger.error("JWT claims string is empty -> Message: {0}", e)
    }

    return false
  }

  fun getUserNameFromJwtToken(token: String): String {
    return Jwts.parser().setSigningKey(jwtSecret)
      .parseClaimsJws(token)
      .body
      .subject
  }
}
