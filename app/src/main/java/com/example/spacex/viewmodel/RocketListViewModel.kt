package com.example.spacex.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.spacex.api.Api
import com.example.spacex.model.RocketsResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RocketListViewModel : ViewModel() {

    private var rocketListData = MutableLiveData<List<RocketsResponse>>()

    fun getRocketList() {

        Api.getClient().getRocketList().enqueue(object : Callback<List<RocketsResponse>> {
            override fun onResponse(
                call: Call<List<RocketsResponse>>,
                response: Response<List<RocketsResponse>>
            ) {
                if (response.body() != null) {
                    rocketListData.value = response.body()!!
                } else {
                    return
                }
                Log.e("ListData", "" + response.body()!!.size);
            }

            override fun onFailure(call: Call<List<RocketsResponse>>, t: Throwable) {
                Log.d("TAG", t.message.toString())
            }
        })

    }

    fun observeRocketListData(): LiveData<List<RocketsResponse>> {
        return rocketListData
    }


}