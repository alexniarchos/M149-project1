package com.exercise1.security.authentication

import com.exercise1.security.JwtProvider
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Service

@Service
class AuthenticationService(
  private val authenticationManager: AuthenticationManager,
  private val jwtProvider: JwtProvider,
  private val userRepository: UserRepository
) {

  fun authenticateUserGetJWT(user: UserDto): String{
    val authentication = authenticationManager.authenticate(
        UsernamePasswordAuthenticationToken(user.username, user.password)
    )

    SecurityContextHolder.getContext().authentication = authentication
    return jwtProvider.generateJwtToken(authentication)
  }

  fun createUser(user: UserDto): User? {
    val existingUser = userRepository.findByUsername(user.username)
    if (existingUser != null) return null
    return userRepository.save(User(null, user.username, BCryptPasswordEncoder().encode(user.password), "ROLE_GUEST"))
  }
}
