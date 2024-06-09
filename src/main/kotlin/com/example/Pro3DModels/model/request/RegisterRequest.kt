package com.example.Pro3DModels.model.request

data class RegisterRequest(
    val password: String,
    val userName: String,
    val userPhone: String
)