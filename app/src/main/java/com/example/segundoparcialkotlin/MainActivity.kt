package com.example.segundoparcialkotlin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    private val listFruits = mutableListOf<Fruit>()

    private lateinit var adapter: Adapter

    private val defaultImg: String = "https://food.fnr.sndimg.com/content/dam/images/food/fullset/2021/09/27/all-the-fruits-cut-whole.jpg.rend.hgtvcom.1280.720.suffix/1632778035320.jpeg"
    private val orangeImg: String = "https://media.istockphoto.com/id/185284489/photo/orange.jpg?s=612x612&w=0&k=20&c=m4EXknC74i2aYWCbjxbzZ6EtRaJkdSJNtekh4m1PspE="
    private val strawberryImg: String = "https://media.istockphoto.com/id/1157946861/photo/red-berry-strawberry-isolated.jpg?s=612x612&w=0&k=20&c=HyxZMbI_e-vDJbrzZkTz5zWCAo1mBEzWbvVlyigbi-E="
    private val bananaImg: String = "https://png.pngtree.com/png-clipart/20220716/ourmid/pngtree-banana-yellow-fruit-banana-skewers-png-image_5944324.png"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView = findViewById(R.id.rvFruit)

        recyclerView.layoutManager = LinearLayoutManager(this)
        adapter = Adapter(listFruits, this)
        recyclerView.adapter = adapter

        adapter.onItemClickListener = {
            navegarDetalle(it)
        }

        getListOfFruits()
    }

    private fun getListOfFruits() {
        CoroutineScope(Dispatchers.IO).launch {
            val call = getRetroFit().create(ApiService::class.java).getFruits("fruit/all")
            val response = call.body()

            runOnUiThread{
                if(call.isSuccessful){
                    val listOfFruits = response
                    listOfFruits?.let {
                        listFruits.addAll(it.sortedBy { fruitsResponse -> fruitsResponse.id })
                    }
                    for (fruitsResponse in listFruits) {
                        when (fruitsResponse.name){
                            "Orange" -> fruitsResponse.image = orangeImg
                            "Strawberry" -> fruitsResponse.image = strawberryImg
                            "Banana" -> fruitsResponse.image = bananaImg
                            else -> fruitsResponse.image = defaultImg
                        }
                    }
                } else {
                    val error = call.errorBody().toString()
                    Log.e("error", error)
                }
                adapter.notifyDataSetChanged()
            }
        }
    }

    private fun navegarDetalle(fruit: Fruit) {
        val intent = Intent(this,DetailFruit::class.java)
        intent.putExtra("fruit", fruit)
        startActivity(intent)
    }

    private fun getRetroFit(): Retrofit {
        return Retrofit
            .Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    companion object {
        const val BASE_URL = "https://fruityvice.com/api/"
    }
}