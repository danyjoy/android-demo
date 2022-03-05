package com.dbp.wrapp.network.responseModels

import androidx.room.Embedded
import java.io.Serializable


data class Address(
   val street: String?,
   val suite: String?,
   val city: String?,
   val zipcode: String?,
   @Embedded
   val geo: Geo?
) : Serializable
