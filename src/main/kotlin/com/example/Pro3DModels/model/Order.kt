package com.example.Pro3DModels.model

import jakarta.persistence.*
import java.time.LocalDate

@Entity
@Table(name = "t_orders")
data class Order(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    val user: User,

    @Column(name = "description")
    val orderDescription: String,

    @Column(name = "order_date", nullable = false)
    val orderDate: LocalDate,

    @Column(name = "order_status", nullable = false)
    val orderStatus: Boolean = false
)