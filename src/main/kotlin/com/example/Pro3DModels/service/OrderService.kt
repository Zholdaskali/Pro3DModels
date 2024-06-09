package com.example.Pro3DModels.service

import com.example.Pro3DModels.model.Order
import com.example.Pro3DModels.model.User
import com.example.Pro3DModels.repository.OrderRepository
import com.example.Pro3DModels.repository.UserRepository
import org.springframework.stereotype.Service
import java.time.LocalDate

@Service
class OrderService(
    private val orderRepository: OrderRepository,
    private val userRepository: UserRepository
) {
    fun createOrder(userId: Long, orderDescription: String): Order {
        val user: User = userRepository.findById(userId)
            .orElseThrow { IllegalArgumentException("User not found") }

        val order = Order(
            user = user,
            orderDescription = orderDescription,
            orderDate = LocalDate.now(),
            orderStatus = false
        )

        return orderRepository.save(order)
    }

    fun deleteOrder(orderId: Long) {
        val order = orderRepository.findById(orderId)
            .orElseThrow { IllegalArgumentException("Order not found") }
        orderRepository.delete(order)
    }

    fun getOrdersByUserId(userId: Long): List<Order> {
        return orderRepository.findByUserId(userId)
    }
}
