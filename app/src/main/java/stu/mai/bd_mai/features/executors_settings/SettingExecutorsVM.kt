package stu.mai.bd_mai.features.executors_settings

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.CreationExtras
import kotlinx.coroutines.launch
import stu.mai.bd_mai.App
import stu.mai.bd_mai.database.AppDatabase
import stu.mai.bd_mai.database.entities.Executor
import stu.mai.bd_mai.features.listoforders.presentation.CheckScreenVM

class SettingExecutorsVM(val database: AppDatabase): ViewModel() {

    fun addExecutor(name: String, phone: String, email: String) {
        viewModelScope.launch {
            val newExecutor = Executor(
                NAME = name,
                PHONE = phone,
                EMAIL = email
            )
            database.getExecutorDao().insertExecutor(newExecutor)
        }
    }


    companion object {
        val factory: androidx.lifecycle.ViewModelProvider.Factory = object : androidx.lifecycle.ViewModelProvider.Factory {
            @Suppress("UNCHECKED_CAST")
            override fun <T : ViewModel> create(modelClass: Class<T>, extras: CreationExtras): T {
                val database = (checkNotNull(extras[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY]) as App).database
                return SettingExecutorsVM(database) as T
            }
        }
    }
}