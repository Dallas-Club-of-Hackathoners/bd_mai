package stu.mai.bd_mai.features.collections.presentation

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import stu.mai.bd_mai.database.AppDatabase
import javax.inject.Inject

@HiltViewModel
class Collection3VM @Inject constructor(val database: AppDatabase): ViewModel() {

    fun getCustomerWithTotalPurchase() = database.getCollectionDao().getCustomerWithTotalPurchase()

}