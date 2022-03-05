package com.dbp.wrapp.ui

import com.dbp.wrapp.network.responseModels.EmpResponse

interface NetworkCallListener {
    fun onStarted()
    fun onSuccess(message: List<EmpResponse>?)
    fun onFailure(message: String)
}