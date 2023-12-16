package stu.mai.bd_mai.features.orders.repositories

import kotlinx.coroutines.flow.Flow
import stu.mai.bd_mai.features.orders.domain.entity.OrderCore

interface CheckRepository {

    suspend fun getOrderByOrderId(orderId: Int): OrderCore

    suspend fun getOrderList(): Flow<List<OrderCore>>
    suspend fun deleteOrder(orderId: Int)

}