package com.experion.assignment.ui

import android.app.Application
import android.content.Context
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.experion.assignment.repository.CaRepository
import com.experion.assignment.models.ResponseModel

class CaFragmentViewModel(application: Application) : AndroidViewModel(application) {

    private var context: Context = application.applicationContext

    var responseModel : LiveData<ResponseModel> = CaRepository.getNewsFeed(context)

    fun cancelJobs() = CaRepository.cancelJob()
}