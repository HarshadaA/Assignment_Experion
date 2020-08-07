package com.experion.assignment.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize


@Parcelize
data class CaModel(
    var title: String? = null,
    var description: String? = null,
    var imageHref: String? = null
) : Parcelable