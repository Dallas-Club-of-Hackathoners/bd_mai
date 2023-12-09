package stu.mai.bd_mai.features.settings.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewmodel.CreationExtras
import stu.mai.bd_mai.App
import stu.mai.bd_mai.database.AppDatabase

//class NewParamsVM(database: AppDatabase) : ViewModel() {
//
//    companion object {
//        val factory: ViewModelProvider.Factory = object : ViewModelProvider.Factory {
//            @Suppress("UNCHECKED_CAST")
//            override fun <T : ViewModel> create(modelClass: Class<T>, extras: CreationExtras): T {
//                val database = (checkNotNull(extras[APPLICATION_KEY]) as App).database
//                return NewParamsVM(database) as T
//            }
//        }
//    }
//}