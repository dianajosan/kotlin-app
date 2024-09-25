package com.example.kotlinapp.presentation

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.kotlinapp.R
import com.example.kotlinapp.data.ApiResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class FirstFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        getApiData()
        return inflater.inflate(R.layout.fragment_first, container, false)
    }

    private fun getApiData() {
        val apiService = RetrofitClient.apiService

        // Call the API method
//        apiService.getBooks().enqueue(object : Callback<List<ApiResponse>> {
//            override fun onResponse(
//                call: Call<List<ApiResponse>>,
//                response: Response<List<ApiResponse>>
//            ) {
//                if (response.isSuccessful) {
//                    val responseData = response.body()
//                    Log.d("DDD", "GET Response: $responseData")
//                } else {
//                    Log.e("DDD", "GET Request Failed: ${response.code()}")
//                }
//            }
//
//            override fun onFailure(call: Call<List<ApiResponse>>, t: Throwable) {
//                Log.e("DDD", "GET Request Failed: $t")
//            }
//        })
    }
}
