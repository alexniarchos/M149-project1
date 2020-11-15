package com.exercise1.security.authentication

import com.exercise1.security.JwtResponse
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@CrossOrigin
@RestController
@RequestMapping("/auth")
class AuthenticationApiImpl(
	private val authenticationService: AuthenticationService,
): AuthenticationApi{

	@PostMapping("/login")
	override fun authenticateUser(@RequestBody user: UserDto): ResponseEntity<Any> =
		ResponseEntity.ok(
				JwtResponse(authenticationService.authenticateUserGetJWT(user), user.username)
		)

	@PostMapping("/signup")
	override fun createUser(user: UserDto): ResponseEntity<Any> {
		val newUser = authenticationService.createUser(user) ?: return ResponseEntity.status(HttpStatus.CONFLICT).build()
		return ResponseEntity.ok(
			JwtResponse(authenticationService.authenticateUserGetJWT(user), newUser.username)
		)
	}
}
