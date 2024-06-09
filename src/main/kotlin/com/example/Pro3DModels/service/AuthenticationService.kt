package com.example.Pro3DModels.service

import com.example.Pro3DModels.encoder.BCryptPasswordEncoder
import com.example.Pro3DModels.exception.UserNotFoundException
import com.example.Pro3DModels.exception.UserAlreadyExistsException
import com.example.Pro3DModels.exception.WrongCredentialsException
import com.example.Pro3DModels.model.User
import com.example.Pro3DModels.repository.UserRepository
import org.springframework.stereotype.Service

@Service
class AuthenticationService(private val userRepository: UserRepository) {

    private val passwordEncoder = BCryptPasswordEncoder(10)

    fun register(userName: String, password: String, userPhone: String): Long? {
        if (userRepository.existsByUserName(userName)) {
            throw UserAlreadyExistsException()
        }

        val encodedPassword = passwordEncoder.hash(password)
        val customer = User(password = encodedPassword, userName = userName, userPhone = userPhone)
        userRepository.save(customer)

        return customer.id
    }

    fun login(userName: String, password: String): Long? {
        val customer = userRepository.findByUserName(userName)
            .orElseThrow { UserNotFoundException() }

        val passwordMatches = customer.password?.let {
            passwordEncoder.check(password, it)
        } ?: false

        if (!passwordMatches) {
            throw WrongCredentialsException()
        }

        return customer.id
    }
}