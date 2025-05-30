package com.example.project_4.model

import com.google.gson.annotations.SerializedName

data class Recipe(
    val id: Int,
    val title: String,
    val image: String,
    val instructions: String?,
    @SerializedName("readyInMinutes") val readyInMinutes: Int?,
    @SerializedName("servings") val servings: Int?,
    var isExpanded: Boolean = false
)
