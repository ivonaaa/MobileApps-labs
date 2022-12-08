package com.example.orwma_lv7

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import layout.MakeupBrandRecyclerAdapter
import retrofit2.Callback
import retrofit2.Call
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val request = ServiceBuilder.buildService(FakerEndpoints::class.java)

        findViewById<Button>(R.id.button).setOnClickListener {
            val call = request.getProducts(findViewById<EditText>(R.id.searchBrandText).text.toString())

            call.enqueue(object : Callback<ArrayList<Product>> {
                override fun onResponse(
                    call: Call<ArrayList<Product>>,
                    response: Response<ArrayList<Product>>
                ) {
                    if (response.isSuccessful) {
                        findViewById<RecyclerView>(R.id.productView).apply {
                            layoutManager =
                                LinearLayoutManager(this@MainActivity)
                            adapter =
                                MakeupBrandRecyclerAdapter(response.body()!!)
                        }
                    }
                }
                override fun onFailure(call: Call<ArrayList<Product>>, t: Throwable) {
                    Log.d("FAIL", t.message.toString())
                }
            })
        }
    }
}