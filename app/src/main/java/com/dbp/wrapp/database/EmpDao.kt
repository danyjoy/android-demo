package com.dbp.wrapp.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.IGNORE
import androidx.room.Query
import com.dbp.wrapp.network.responseModels.EmpResponse

@Dao
interface EmpDao {

    /**
     * to insert a single user object
     */
    @Insert(onConflict = IGNORE)
    suspend fun insert(user: EmpResponse)

    /**
     * Get all the user details from database
     */
    @Query("SELECT * FROM emp_table")
    fun readDatas(): LiveData<List<EmpResponse>>
}