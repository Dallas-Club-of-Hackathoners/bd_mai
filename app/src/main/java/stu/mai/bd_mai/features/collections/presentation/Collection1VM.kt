package stu.mai.bd_mai.features.collections.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import stu.mai.bd_mai.database.AppDatabase
import stu.mai.bd_mai.features.collections.domain.entity.MaterialWithTotalUsed
import javax.inject.Inject

@HiltViewModel
class Collection1VM @Inject constructor(val database: AppDatabase): ViewModel() {

     fun getMaterialWithTotalUsed() = database.getCollectionDao().getMaterialsWithTotalUsed()
}