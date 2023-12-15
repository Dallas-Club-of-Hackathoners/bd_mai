package stu.mai.bd_mai.features.infocard.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewmodel.CreationExtras
import dagger.hilt.android.lifecycle.HiltViewModel
import stu.mai.bd_mai.App
import stu.mai.bd_mai.database.AppDatabase
import javax.inject.Inject
@HiltViewModel
class CardOrderVM @Inject constructor(database: AppDatabase): ViewModel() {
    val ordersList = database.getOrderDao().getAllOrders()

//    companion object {
//        val factory: ViewModelProvider.Factory = object : ViewModelProvider.Factory {
//            @Suppress("UNCHECKED_CAST")
//            override fun <T : ViewModel> create(modelClass: Class<T>, extras: CreationExtras): T {
//                val database = (checkNotNull(extras[APPLICATION_KEY]) as App).database
//                return CardOrderVM(database) as T
//            }
//        }
//    }
}

