package com.dbp.wrapp.ui.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.dbp.wrapp.database.EmpDatabase

@Suppress("UNCHECKED_CAST")
class MainViewModelFactory(
    private val db: EmpDatabase
) : ViewModelProvider.NewInstanceFactory(){

    /**
     * Viewmodel factory to pass db instance
     */
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return MainViewModel(db) as T
    }
}