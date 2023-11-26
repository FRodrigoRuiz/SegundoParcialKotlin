package com.example.segundoparcialkotlin

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Fruit (
    val name: String?,
    val id: Int,
    val nutrition: Nutrition,
    var image: String
) : Parcelable

@Parcelize
data class Nutrition (
    val calories: Int,
    val fat: Float,
    val sugar: Float,
    val carbohydrates: Float,
    val protein: Float
) : Parcelable