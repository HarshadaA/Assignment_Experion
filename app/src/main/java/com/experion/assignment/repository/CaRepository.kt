package com.experion.assignment.repository

import android.content.Context
import androidx.lifecycle.LiveData
import com.experion.assignment.database.CaRoomDatabase
import com.experion.assignment.api.RetrofitBuilder
import com.experion.assignment.models.ResponseModel
import com.experion.assignment.utils.isNetworkAvailable
import kotlinx.coroutines.*
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.Dispatchers.Main

object CaRepository {

    private var getNewsFeedsJob: CompletableJob? = null

    fun getNewsFeed(context: Context): LiveData<ResponseModel> {
        getNewsFeedsJob = Job()
        return object : LiveData<ResponseModel>() {
            override fun onActive() {
                super.onActive()
                getNewsFeedsJob?.let { job ->
                    CoroutineScope(IO + job).launch {
                        var responseModel: ResponseModel? = null
                        if (isNetworkAvailable(context)) {
                            responseModel = RetrofitBuilder.apiService.getNewsResponse()
                            responseModel.let {
                                CaRoomDatabase.getDatabase(context).newsDao()
                                    .insertNewsData(it)
                            }
                        } else {
                            responseModel =
                                CaRoomDatabase.getDatabase(context).newsDao().getAllNews()
                        }


                        withContext(Main) {
                            value = responseModel
                            job.complete()
                        }
                    }
                }
            }
        }
    }

    fun cancelJob() {
        getNewsFeedsJob?.cancel()
    }
}