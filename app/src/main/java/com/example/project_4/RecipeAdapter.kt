package com.example.project_4

import android.text.Html
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.project_4.model.Recipe

class RecipeAdapter(private val recipes: List<Recipe>) :
    RecyclerView.Adapter<RecipeAdapter.RecipeViewHolder>() {

    class RecipeViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val title: TextView = view.findViewById(R.id.recipeTitle)
        val image: ImageView = view.findViewById(R.id.recipeImage)
        val instructions: TextView = view.findViewById(R.id.recipeSummary)
        val readyTime: TextView = view.findViewById(R.id.recipeReadyTime)
        val servings: TextView = view.findViewById(R.id.recipeServings)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecipeViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_recipe, parent, false)
        return RecipeViewHolder(view)
    }

    override fun onBindViewHolder(holder: RecipeViewHolder, position: Int) {
        val recipe = recipes[position]

        holder.title.text = recipe.title

        val fullText = if (!recipe.instructions.isNullOrBlank()) {
            Html.fromHtml(recipe.instructions, Html.FROM_HTML_MODE_LEGACY).toString()
        } else {
            "No instructions available"
        }
        holder.instructions.text = fullText

        holder.instructions.maxLines = if (recipe.isExpanded) Int.MAX_VALUE else 4
        holder.instructions.ellipsize = if (recipe.isExpanded) null else TextUtils.TruncateAt.END

        holder.instructions.setOnClickListener {
            recipe.isExpanded = !recipe.isExpanded
            notifyItemChanged(position)
        }

        holder.readyTime.text = "Ready in ${recipe.readyInMinutes ?: "?"} min"
        holder.servings.text = "Serves ${recipe.servings ?: "?"}"

        Glide.with(holder.itemView.context)
            .load(recipe.image)
            .placeholder(R.drawable.placeholder)
            .into(holder.image)
    }

    override fun getItemCount(): Int = recipes.size
}
