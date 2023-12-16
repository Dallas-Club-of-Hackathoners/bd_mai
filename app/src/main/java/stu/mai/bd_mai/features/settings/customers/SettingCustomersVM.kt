package stu.mai.bd_mai.features.settings.customers

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import stu.mai.bd_mai.database.AppDatabase
import stu.mai.bd_mai.database.entities.Customer
import javax.inject.Inject


@HiltViewModel
class SettingCustomersVM @Inject constructor(val database: AppDatabase) : ViewModel() {

    fun addCustomer(name: String, phone: String, email: String) {
        viewModelScope.launch {
            val newCustomer = Customer(
                NAME = name,
                PHONE = phone,
                EMAIL = email
            )
            database.getCustomerDao().insertCustomer(newCustomer)
        }
    }


}