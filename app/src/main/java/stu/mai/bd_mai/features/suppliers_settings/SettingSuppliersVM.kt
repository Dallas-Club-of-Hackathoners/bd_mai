package stu.mai.bd_mai.features.suppliers_settings

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.CreationExtras
import kotlinx.coroutines.launch
import stu.mai.bd_mai.App
import stu.mai.bd_mai.database.AppDatabase
import stu.mai.bd_mai.database.entities.Supplier

class SettingSuppliersVM(val database: AppDatabase): ViewModel() {

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


    companion object {
        val factory: androidx.lifecycle.ViewModelProvider.Factory = object : androidx.lifecycle.ViewModelProvider.Factory {
            @Suppress("UNCHECKED_CAST")
            override fun <T : ViewModel> create(modelClass: Class<T>, extras: CreationExtras): T {
                val database = (checkNotNull(extras[APPLICATION_KEY]) as App).database
                return SettingSuppliersVM(database) as T
            }
        }
    }
}