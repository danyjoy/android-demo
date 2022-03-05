package com.dbp.wrapp.network.responseModels

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import java.io.Serializable

@Entity(tableName = "emp_table")
data class EmpResponse(
    @PrimaryKey
    val id : Int,
    val name : String?,
    val email :String?,
    val username: String?,
    @SerializedName("profile_image")
    val profileImage: String?,
    @Embedded
    val address: Address?,
    val phone: String?,
    val website: String?,
    @Embedded
    val company: Company?
) : Serializable
