package com.dbp.wrapp.network
import com.dbp.wrapp.network.responseModels.EmpResponse
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface MyApi {


    /**
     * GET method which will return the response as
     * a list of EmpResponse object
     */
    @GET("v2/5d565297300000680030a986")
    suspend fun getEmpData(
    ) : Response<List<EmpResponse>>

    companion object{

        const val BASE_URL = "http://www.mocky.io/"
        operator fun invoke() : MyApi{
            return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(MyApi::class.java)
        }
    }
}