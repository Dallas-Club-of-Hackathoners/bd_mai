package stu.mai.bd_mai.features.orders.presentation.list

import androidx.compose.runtime.Stable
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow
import stu.mai.bd_mai.UnidirectionalViewModel
import stu.mai.bd_mai.features.orders.domain.entity.OrderCore

@Stable
interface CheckScreenContract : UnidirectionalViewModel<CheckScreenContract.State, CheckScreenContract.Event> {

        @Stable
        data class State(
            val orderList : Flow<List<OrderCore>> = emptyFlow(),

        )

        @Stable
        sealed class Event {
            object OnGetOrderList : Event()
        }
}
