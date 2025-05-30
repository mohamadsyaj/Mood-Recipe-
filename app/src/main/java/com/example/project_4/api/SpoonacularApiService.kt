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
        @Query("apiKey") apiKey: String = "448c28b1ccdb4e799596165d190fb132"
    ): RecipeResponse

    @GET("recipes/informationBulk")
    suspend fun getBulkRecipeInfo(
        @Query("ids") ids: String,
        @Query("apiKey") apiKey: String = "448c28b1ccdb4e799596165d190fb132"
    ): List<Recipe>
}
