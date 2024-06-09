package com.example.Pro3DModels.service

import com.example.Pro3DModels.model.User
import com.example.Pro3DModels.repository.UserRepository
import com.example.Pro3DModels.exception.UserNotFoundException
import org.springframework.stereotype.Service

@Service
class UserService(
    private val userRepository: UserRepository
) {
    fun getById(userId: Long): User {
        return userRepository.findById(userId).orElseThrow { UserNotFoundException() }
    }
}