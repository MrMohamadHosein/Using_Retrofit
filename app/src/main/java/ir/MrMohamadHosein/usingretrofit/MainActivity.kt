package ir.MrMohamadHosein.UsingRetrofit

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import ir.MrMohamadHosein.UsingRetrofit.databinding.ActivityMainBinding
import ir.MrMohamadHosein.UsingRetrofit.model.User
import org.json.JSONArray
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

const val BASE_URL = "https://jsonplaceholder.typicode.com/"

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // 1. add library
        // 2. internet permission
        // 3. create interface for our fun
        // 4. init Retrofit and ApiService

        binding.btnGetData.setOnClickListener {

            val retrofit = Retrofit
                .Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory( GsonConverterFactory.create() )
                .build()

            val apiService = retrofit.create( ApiService::class.java )

            val call = apiService.getUsers()
            call.enqueue( object :Callback<List<User>> {
                override fun onResponse(call: Call<List<User>>, response: Response<List<User>>) {
                        val data = response.body()
                    if (data != null) {
                        data.forEach {

                            it.name
                            Log.v("testRetrofit" , it.name)


                        }
                    }
                }

                override fun onFailure(call: Call<List<User>>, t: Throwable) {
                    Toast.makeText(this@MainActivity, t.message, Toast.LENGTH_SHORT).show()
                }

            } )

        }

    }
}