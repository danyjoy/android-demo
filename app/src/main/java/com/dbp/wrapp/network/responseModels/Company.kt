package com.dbp.wrapp.network.responseModels

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Company(
    @SerializedName("name")
    val companyName: String?,
    val catchPhrase: String?,
    val bs: String?
) : Serializable
