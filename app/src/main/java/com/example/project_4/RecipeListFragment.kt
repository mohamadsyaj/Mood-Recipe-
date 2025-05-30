package com.example.project_4

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.project_4.viewmodel.RecipeViewModel
import com.example.project_4.viewmodel.RecipeViewModelFactory
import com.example.project_4.repository.RecipeRepository
import com.example.project_4.api.RetrofitBuilder

class RecipeListFragment : Fragment() {

    private lateinit var viewModel: RecipeViewModel
    private lateinit var moodText: TextView
    private lateinit var recyclerView: RecyclerView
    private lateinit var progressBar: ProgressBar
    private lateinit var errorText: TextView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view = inflater.inflate(R.layout.fragment_recipe_list, container, false)

        moodText = view.findViewById(R.id.selectedMoodText)
        recyclerView = view.findViewById(R.id.recipeRecyclerView)
        progressBar = view.findViewById(R.id.recipeProgressBar)
        errorText = view.findViewById(R.id.errorText)

        recyclerView.layoutManager = LinearLayoutManager(requireContext())


        val selectedMood = arguments?.getString("selectedMood") ?: "unknown"
        moodText.text = getString(R.string.top_recipes, selectedMood)


        val repository = RecipeRepository(RetrofitBuilder.apiService)
        val factory = RecipeViewModelFactory(repository)
        viewModel = ViewModelProvider(this, factory)[RecipeViewModel::class.java]

        viewModel.recipes.observe(viewLifecycleOwner) { recipeList ->
            progressBar.visibility = View.GONE
            if (recipeList.isNotEmpty()) {
                recyclerView.adapter = RecipeAdapter(recipeList)
                recyclerView.visibility = View.VISIBLE
                errorText.visibility = View.GONE
            } else {
                errorText.text = "No recipes found."
                errorText.visibility = View.VISIBLE
            }
        }


        progressBar.visibility = View.VISIBLE
        recyclerView.visibility = View.GONE
        errorText.visibility = View.GONE
        viewModel.fetchRecipesForMood(selectedMood)

        return view
    }
}
