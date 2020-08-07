package com.experion.assignment

import com.experion.assignment.api.RetrofitBuilder
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch
import org.junit.Test

class ApiTest {
    @Test
    fun isResponseAvailable() {
        CoroutineScope(IO).launch {
            val responseModel = RetrofitBuilder.apiService.getNewsResponse()
            assert(responseModel.rows.isNullOrEmpty())
        }
    }

}