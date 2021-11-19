package com.example.cryptoapp2.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.cryptoapp2.R
import com.example.cryptoapp2.adapter.CryptoRecyclerViewAdapter
import com.example.cryptoapp2.databinding.ActivityMainBinding
import com.example.cryptoapp2.model.CryptoModel
import com.example.cryptoapp2.service.CryptoAPI
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {

    // https://api.nomics.com/v1
    // api key - fc673eb872161cda9a1e7639b8b31f43fa24f441
    // https://api.nomics.com/v1/prices?key=fc673eb872161cda9a1e7639b8b31f43fa24f441

    private lateinit var binding: ActivityMainBinding

    private val BASE_URL = "https://api.nomics.com/v1/"

    private var crytoModelsArrayList: ArrayList<CryptoModel>? = null
    private var cryptoAdapter = CryptoRecyclerViewAdapter(arrayListOf())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        val window = this.window
        window.addFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS)
        window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION)
        window.statusBarColor = this.getResources().getColor(android.R.color.transparent)
        window.navigationBarColor = this.getResources().getColor(android.R.color.transparent)

        binding.recyclerViewMain.layoutManager = LinearLayoutManager(this)


        loadData()

    }

    private fun loadData() {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val call = retrofit.create(CryptoAPI::class.java).getData()

        call.enqueue(object : Callback<List<CryptoModel>> {
            override fun onResponse(
                call: Call<List<CryptoModel>>,
                response: Response<List<CryptoModel>>
            ) {
                if (response.isSuccessful) {
                    response.body()?.let {
                        crytoModelsArrayList = ArrayList(it)

                        crytoModelsArrayList?.let {
                            cryptoAdapter = CryptoRecyclerViewAdapter(it)
                            binding.recyclerViewMain.adapter = cryptoAdapter
                            cryptoAdapter.notifyDataSetChanged()
                        }

                        /*for (cryptoModel: CryptoModel in crytoModelsArrayList!!) {
                            println(cryptoModel.currency)
                            println(cryptoModel.price)

                        }*/
                    }
                }
            }

            override fun onFailure(call: Call<List<CryptoModel>>, t: Throwable) {
                t.printStackTrace()
            }

        })


    }

}