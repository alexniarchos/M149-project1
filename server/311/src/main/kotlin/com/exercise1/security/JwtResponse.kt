package com.exercise1.security

class JwtResponse(
	val token: String,
	val username: String,
	val type: String = "Bearer"
)
