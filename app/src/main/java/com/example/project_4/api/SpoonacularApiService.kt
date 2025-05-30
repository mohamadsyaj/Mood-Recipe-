package com.example.project_4.api

import com.example.project_4.model.Recipe
import com.example.project_4.model.RecipeResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface SpoonacularApiService {
    @GET("recipes/complexSearch")
    suspend fun searchRecipes(
        @Query("query") query: String,
        @Query("number") number: Int = 10,
        @Query("addRecipeInformation") addInfo: Boolean = false,
        @Query("apiKey") apiKey: String = ""
    ): RecipeResponse

    @GET("recipes/informationBulk")
    suspend fun getBulkRecipeInfo(
        @Query("ids") ids: String,
        @Query("apiKey") apiKey: String = ""
    ): List<Recipe>
}
