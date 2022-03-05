package com.dbp.wrapp.network.responseModels

import java.io.Serializable

data class Geo(
    val lat: String?,
    val lng: String?
) : Serializable
