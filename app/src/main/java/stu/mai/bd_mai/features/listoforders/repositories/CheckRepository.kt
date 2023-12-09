package stu.mai.bd_mai.features.listoforders.repositories

import kotlinx.coroutines.flow.Flow
import stu.mai.bd_mai.database.dao.CustomerDao
import stu.mai.bd_mai.database.entities.Order
import javax.inject.Inject

//class CheckRepository @Inject constructor(
//    private val customerDao: CustomerDao
//) {
//    suspend fun getAllCustomers() = customerDao.getAllCustomers()
//
//} //todo все функции для работы с базой данных в этом экране