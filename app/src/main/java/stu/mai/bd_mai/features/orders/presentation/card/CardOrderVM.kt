package stu.mai.bd_mai.features.orders.presentation.card

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext
import stu.mai.bd_mai.features.orders.domain.usecase.GetOrderInfoUseCase
import javax.inject.Inject
@HiltViewModel
class CardOrderVM @Inject constructor(
    private val getOrderInfoUseCase: GetOrderInfoUseCase
): ViewModel(), CardOrderContract {

    private val mutableScreenState = MutableStateFlow(CardOrderContract.State())
    override val state: StateFlow<CardOrderContract.State> = mutableScreenState.asStateFlow()

    override fun event(event: CardOrderContract.Event) {
        when (event) {
            is CardOrderContract.Event.OnGetOrder -> getOrder(event.orderId)
        }
    }

    private fun getOrder(orderId: Int) = viewModelScope.launch(Dispatchers.IO) {
        try {
            val order = getOrderInfoUseCase.getOrderById(orderId)
            mutableScreenState.update { it.copy(order = order) }
        } catch (e: Exception) {
            // todo handle exception
            // add side effect?
        }

    }

    fun deleteOrder(orderId: Int) = viewModelScope.launch(Dispatchers.IO) {
        try {
            getOrderInfoUseCase.deleteOrderById(orderId)
        } catch (e: Exception) {
            // todo handle exception
            // add side effect?
        }
    }

}

