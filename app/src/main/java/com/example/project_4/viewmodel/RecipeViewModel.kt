package com.example.project_4.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.project_4.model.Recipe
import com.example.project_4.repository.RecipeRepository
import kotlinx.coroutines.launch

class RecipeViewModel(private val repository: RecipeRepository) : ViewModel() {

    private val _recipes = MutableLiveData<List<Recipe>>()
    val recipes: LiveData<List<Recipe>> = _recipes

    fun fetchRecipesForMood(mood: String) {
        val keyword = when (mood.lowercase()) {
            "happy" -> "fresh"
            "sad" -> "chocolate"
            "angry" -> "comfort food"
            "bored" -> "easy snack"
            else -> mood
        }

        viewModelScope.launch {
            try {
                val result = repository.getRecipesForMood(keyword)
                _recipes.value = result
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}