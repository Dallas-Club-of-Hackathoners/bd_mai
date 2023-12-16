package stu.mai.bd_mai.features.orders.presentation.card

import androidx.compose.runtime.Stable
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow
import stu.mai.bd_mai.UnidirectionalViewModel
import stu.mai.bd_mai.features.orders.domain.entity.OrderCore

@Stable
interface CardOrderContract: UnidirectionalViewModel<CardOrderContract.State, CardOrderContract.Event> {

    @Stable
    data class State(
        val order: OrderCore? = null,
    )

    @Stable
    sealed class Event {
        data class OnGetOrder(val orderId: Int) : Event()
    }
}