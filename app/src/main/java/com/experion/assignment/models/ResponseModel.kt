package com.experion.assignment.models

import android.os.Parcelable
import androidx.annotation.NonNull
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "news_data")
data class ResponseModel(
    var title : String = "",
    var rows: List<CaModel>? = ArrayList()
) : Parcelable{
    @PrimaryKey(autoGenerate = true)
    @NonNull
    var id: Int = 1
}