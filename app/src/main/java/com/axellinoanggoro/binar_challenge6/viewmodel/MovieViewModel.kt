package com.axellinoanggoro.binar_challenge6.viewmodel


import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.axellinoanggoro.binar_challenge6.model.GetPopularMovie
import com.axellinoanggoro.binar_challenge6.model.ResultPopularMovie
import com.axellinoanggoro.binar_challenge6.network.ApiClient
import com.axellinoanggoro.binar_challenge6.network.ApiService
import dagger.hilt.android.lifecycle.HiltViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class MovieViewmodel @Inject constructor(var api : ApiService) : ViewModel() {
    var liveDataMovie: MutableLiveData<List<ResultPopularMovie>> = MutableLiveData()

    fun callTmdb() {
        api.getPopularMovie().enqueue(object : Callback<GetPopularMovie> {
            override fun onResponse(
                call: Call<GetPopularMovie>,
                response: Response<GetPopularMovie>
            ) {
                if (response.isSuccessful) {
                    liveDataMovie.postValue(response.body()?.results)
                } else {
                    liveDataMovie.postValue(null)
                }
            }

            override fun onFailure(call: Call<GetPopularMovie>, t: Throwable) {
                liveDataMovie.postValue(null)
            }

        })
    }
}