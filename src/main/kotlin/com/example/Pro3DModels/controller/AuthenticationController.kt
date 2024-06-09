package com.example.Pro3DModels.encoder

import com.example.Pro3DModels.model.request.LoginRequest
import com.example.Pro3DModels.model.request.RegisterRequest
import com.example.Pro3DModels.service.AuthenticationService
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("api/v1/auth")
@CrossOrigin(origins = ["*"])
class AuthenticationController(
    private val authenticationService: AuthenticationService
) {

    @PostMapping("/register")
    fun register(@RequestBody registerRequest: RegisterRequest): Long? {
        return authenticationService.register(registerRequest.userName, registerRequest.password, registerRequest.userPhone)
    }

    @PostMapping("/login")
    fun login(@RequestBody loginRequest: LoginRequest): Long? {
        return authenticationService.login(loginRequest.userName, loginRequest.password)
    }
}
////
//{
//    "userName": "John Doe",
//    "userPhone": "1234567890",
//    "password": "password1231"
//}
//{
//    "userName": "Zholdaskali Erkebulan",
//    "userPhone": "87478408845",
//    "password": "password1231"
//}