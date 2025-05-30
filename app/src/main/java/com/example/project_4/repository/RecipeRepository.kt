package com.example.project_4.repository

import com.example.project_4.api.SpoonacularApiService
import com.example.project_4.model.Recipe

class RecipeRepository(private val api: SpoonacularApiService) {
    suspend fun getRecipesForMood(mood: String): List<Recipe> {
        val searchResponse = api.searchRecipes(mood)
        val ids = searchResponse.results.map { it.id }.joinToString(",")

        return if (ids.isNotEmpty()) {
            api.getBulkRecipeInfo(ids)
        } else {
            emptyList()
        }
    }
}
