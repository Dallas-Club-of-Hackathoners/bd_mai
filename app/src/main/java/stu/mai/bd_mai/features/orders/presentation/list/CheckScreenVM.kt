package stu.mai.bd_mai.features.orders.presentation.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import stu.mai.bd_mai.features.orders.domain.usecase.GetOrderListUseCase
import javax.inject.Inject

@HiltViewModel
class CheckScreenVM @Inject constructor(
    private val getOrderListUseCase: GetOrderListUseCase
): ViewModel(), CheckScreenContract {

    private val mutableScreenState = MutableStateFlow(CheckScreenContract.State())
    override val state: StateFlow<CheckScreenContract.State> = mutableScreenState.asStateFlow()

    override fun event(event: CheckScreenContract.Event) {
        when (event) {
            CheckScreenContract.Event.OnGetOrderList -> getOrderList()
        }
    }

    private fun getOrderList() = viewModelScope.launch(Dispatchers.IO) {

        try {
            val orderList = getOrderListUseCase.getOrderList()
            val tmp = orderList.first()
            mutableScreenState.update { it.copy(orderList = orderList) }
        } catch (e: Exception) {
            // todo handle exception
            // add side effect?
        }
    }

}