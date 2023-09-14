package com.example.examen_crud.adapters.recipe

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.examen_crud.R
import com.example.examen_crud.models.Recipe

class RecipeAdapter(
    private var recipeList: List<Recipe>,
) : RecyclerView.Adapter<RecipeViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecipeViewHolder {
        val itemView = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.item_recipe, parent, false)
        return RecipeViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: RecipeViewHolder, position: Int) {
        val currentRecipe = recipeList[position]
        holder.bind(currentRecipe)
    }

    override fun getItemCount() = recipeList.size
}
