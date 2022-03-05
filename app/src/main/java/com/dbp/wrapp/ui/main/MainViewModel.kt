package com.dbp.wrapp.ui.main

import androidx.lifecycle.ViewModel
import com.dbp.wrapp.database.EmpDatabase
import com.dbp.wrapp.repositories.EmpRepository
import com.dbp.wrapp.ui.NetworkCallListener
import com.dbp.wrapp.util.Coroutines
import org.kodein.di.Instance
import org.kodein.di.KodeinAware
import org.kodein.di.generic.instance

class MainViewModel(
    private val db: EmpDatabase
) : ViewModel(){

    var networkCallListener : NetworkCallListener? = null

    /**
     * Gets data from backend as LiveData
     */
    fun getEmployeeDataFromDB() = EmpRepository(db).readAllData

    /**
     * Gets data from API when there is no data in the backend
     */
    fun getEmployeeData(){
        networkCallListener?.onStarted()
        Coroutines.main {
            val response = EmpRepository(db).getData()
            if(response.isSuccessful){
                response.body().let {
                    networkCallListener?.onSuccess(it)
                }

            }
        }
    }
}