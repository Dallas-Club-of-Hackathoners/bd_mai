package stu.mai.bd_mai.features.orders.domain.usecase

import stu.mai.bd_mai.features.orders.repositories.CheckRepository
import javax.inject.Inject

class GetOrderInfoUseCase @Inject constructor(
    private val checkRepository: CheckRepository
) {
    suspend fun getOrderById(orderId: Int) =
        checkRepository.getOrderByOrderId(orderId)

    suspend fun deleteOrderById(orderId: Int) =
        checkRepository.deleteOrder(orderId)
}