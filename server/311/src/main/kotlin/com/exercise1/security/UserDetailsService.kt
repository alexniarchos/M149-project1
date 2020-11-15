package com.exercise1.security

import com.exercise1.security.authentication.UserRepository
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class UserDetailsService(
	private val userRepository: UserRepository
) : UserDetailsService {
 
	@Override
	@Transactional
	override fun loadUserByUsername(username: String): UserDetails {
		val user = userRepository.findByUsername(username)
 
		return UserPrinciple.build(user!!)
	}
}
