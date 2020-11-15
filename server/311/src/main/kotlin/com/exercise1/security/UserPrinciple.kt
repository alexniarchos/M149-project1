package com.exercise1.security

import com.exercise1.security.authentication.User
import com.fasterxml.jackson.annotation.JsonIgnore

import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails

import java.util.ArrayList

class UserPrinciple(
  val id: Long,
  private val username: String,
  @JsonIgnore private val password: String,
  val authorities: ArrayList<out GrantedAuthority>,
) : UserDetails {
  companion object {
    const val serialVersionUID: Long = 1L

    fun build(user: User): UserPrinciple {
      val authorities = ArrayList<GrantedAuthority>()
      authorities.add(SimpleGrantedAuthority(user.role))

      return UserPrinciple(
        user.id!!,
        user.username,
        user.password,
        authorities
      )
    }
  }

  override fun getAuthorities(): MutableCollection<out GrantedAuthority> = authorities

  override fun getPassword(): String = password

  override fun getUsername(): String = username

  override fun isAccountNonExpired(): Boolean = true

  override fun isAccountNonLocked(): Boolean = true

  override fun isCredentialsNonExpired(): Boolean = true

  override fun isEnabled(): Boolean = true
}
