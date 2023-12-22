package stu.mai.bd_mai.features.collections.presentation

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import stu.mai.bd_mai.database.AppDatabase
import javax.inject.Inject

@HiltViewModel
class Collection2VM @Inject constructor(val database: AppDatabase): ViewModel() {

    fun getSupplierWithTotalMaterials() = database.getCollectionDao().getSuppliersWithTotalMaterials()

}