package com.example.Pro3DModels.controller

import com.example.Pro3DModels.model.Order
import com.example.Pro3DModels.model.request.OrderRequest
import com.example.Pro3DModels.service.OrderService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/v1/orders")
@CrossOrigin(origins = ["*"])
class OrderController(
    private val orderService: OrderService
) {
    @PostMapping("/create")
    fun createOrder(@RequestBody request: OrderRequest): ResponseEntity<Order> {
        return try {
            val order = orderService.createOrder(request.userId, request.orderDescription)
            ResponseEntity(order, HttpStatus.CREATED)
        } catch (e: IllegalArgumentException) {
            ResponseEntity(HttpStatus.NOT_FOUND)
        } catch (e: Exception) {
            ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR)
        }
    }

    @DeleteMapping("/delete/{orderId}")
    fun deleteOrder(@PathVariable orderId: Long): ResponseEntity<String> {
        return try {
            orderService.deleteOrder(orderId)
            ResponseEntity("Order deleted successfully", HttpStatus.OK)
        } catch (e: IllegalArgumentException) {
            ResponseEntity("Order not found", HttpStatus.NOT_FOUND)
        } catch (e: Exception) {
            ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR)
        }
    }

    @GetMapping("/{userId}")
    fun getOrdersByUserId(@PathVariable userId: Long): List<Order> {
        return orderService.getOrdersByUserId(userId)
    }

}