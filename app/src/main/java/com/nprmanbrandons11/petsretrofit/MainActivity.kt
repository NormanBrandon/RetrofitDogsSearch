package com.nprmanbrandons11.petsretrofit

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.AttributeSet
import android.view.View
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import com.nprmanbrandons11.petsretrofit.databinding.ActivityMainBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class MainActivity : AppCompatActivity(),SearchView.OnQueryTextListener,
    android.widget.SearchView.OnQueryTextListener {

    private lateinit var binding: ActivityMainBinding
    private val dogsImages = mutableListOf<String>()
    private lateinit var adapter:RVAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root

        setContentView(view)
        binding.svmain.setOnQueryTextListener(this)
        initRecycler()
    }
    private fun getRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://dog.ceo/api/breed/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    }

    private fun searchByName(query:String){
        CoroutineScope(Dispatchers.IO).launch {
            val call:Response<DogsResponse> = getRetrofit().create(ApiService::class.java).getDogsByBreed("$query/images")
            val puppies:DogsResponse? = call.body()
            runOnUiThread {
                if(call.isSuccessful) {
                    val images:List<String> = puppies?.images?: emptyList()
                    dogsImages.clear()
                    dogsImages.addAll(images)
                    adapter.notifyDataSetChanged()
                }
                else showerror()
            }
        }

    }

    private fun showerror() {
        Toast.makeText(this,"Error",Toast.LENGTH_SHORT).show()
    }

    private fun initRecycler(){
         adapter = RVAdapter(dogsImages)
        binding.rvDogs.layoutManager =LinearLayoutManager(this)
        binding.rvDogs.adapter = adapter

    }

    override fun onQueryTextSubmit(query: String?): Boolean {
        if (!query.isNullOrEmpty()){
            searchByName(query.lowercase())
        }
        return true
    }

    override fun onQueryTextChange(newText: String?): Boolean {
        return true
    }


}

