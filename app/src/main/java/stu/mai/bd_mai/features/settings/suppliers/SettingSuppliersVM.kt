package stu.mai.bd_mai.features.settings.suppliers

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import stu.mai.bd_mai.database.AppDatabase
import stu.mai.bd_mai.database.entities.Supplier
import javax.inject.Inject

@HiltViewModel
class SettingSuppliersVM @Inject constructor(val database: AppDatabase): ViewModel() {

    fun addSupplier(name: String, phone: String, email: String) {
        viewModelScope.launch {
            val newSupplier = Supplier(
                NAME = name,
                PHONE = phone,
                EMAIL = email
            )
            database.getSupplierDao().insertSupplier(newSupplier)
        }
    }

}