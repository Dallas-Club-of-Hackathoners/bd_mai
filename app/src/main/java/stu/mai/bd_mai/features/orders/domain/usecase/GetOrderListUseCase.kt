package stu.mai.bd_mai.features.orders.domain.usecase

import stu.mai.bd_mai.features.orders.repositories.CheckRepository
import javax.inject.Inject

class GetOrderListUseCase @Inject constructor(
    private val checkRepository: CheckRepository
) {
    suspend fun getOrderList() =
        checkRepository.getOrderList()
}