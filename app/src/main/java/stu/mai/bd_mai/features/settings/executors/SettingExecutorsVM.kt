package stu.mai.bd_mai.features.settings.executors

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import stu.mai.bd_mai.database.AppDatabase
import stu.mai.bd_mai.database.entities.Executor
import javax.inject.Inject

@HiltViewModel
class SettingExecutorsVM @Inject constructor(val database: AppDatabase): ViewModel() {

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


//    companion object {
//        val factory: androidx.lifecycle.ViewModelProvider.Factory = object : androidx.lifecycle.ViewModelProvider.Factory {
//            @Suppress("UNCHECKED_CAST")
//            override fun <T : ViewModel> create(modelClass: Class<T>, extras: CreationExtras): T {
//                val database = (checkNotNull(extras[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY]) as App).database
//                return SettingExecutorsVM(database) as T
//            }
//        }
//    }
}