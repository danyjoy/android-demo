package com.dbp.wrapp.repositories

import androidx.lifecycle.LiveData
import com.dbp.wrapp.database.EmpDatabase
import com.dbp.wrapp.network.MyApi
import com.dbp.wrapp.network.responseModels.EmpResponse
import org.kodein.di.KodeinAware
import org.kodein.di.generic.instance
import retrofit2.Response


class EmpRepository(private val db: EmpDatabase){

//    override val kodein by kodein
//
//    val db : EmpDatabase by kodein
    val empDao = db.getUserDao()



    suspend fun getData(): Response<List<EmpResponse>> {

        val response = MyApi().getEmpData()
        val list = response.body()
        addUser(list!!)
        return response
    }

    val readAllData: LiveData<List<EmpResponse>> = empDao.readDatas()
//
    suspend fun addUser(user: List<EmpResponse>){
        user.forEach {
            empDao.insert(it)
        }

    }

//    override val kodein by kodein
}