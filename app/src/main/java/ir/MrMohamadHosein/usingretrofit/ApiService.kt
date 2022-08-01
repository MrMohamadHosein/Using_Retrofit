package ir.MrMohamadHosein.UsingRetrofit

import ir.MrMohamadHosein.UsingRetrofit.model.User
import retrofit2.Call
import retrofit2.http.GET

interface ApiService {

    @GET("users")
    fun getUsers() :Call<List<User>>

}