package com.example.examen_crud.adapters.recipe

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.examen_crud.R
import com.example.examen_crud.activities.RecipeDescriptionActivity
import com.example.examen_crud.models.Recipe


class RecipeAdapter(
    private var recipeList: List<Recipe>,
    private val context: Context
) : RecyclerView.Adapter<RecipeViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecipeViewHolder {
        val itemView = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.item_recipe, parent, false)
        return RecipeViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: RecipeViewHolder, position: Int) {
        val currentRecipe = recipeList[position]
        holder.bind(currentRecipe, context)

        // Change to Layout Description Recipe
        holder.itemView.setOnClickListener { v ->
            val intent = Intent(v.context, RecipeDescriptionActivity::class.java)
            intent.putExtra("recipe", currentRecipe)
            v.context.startActivity(intent)
        }
    }

    override fun getItemCount() = recipeList.size

    fun updateItems(recipeList: List<Recipe>) {
        this.recipeList = recipeList
        notifyDataSetChanged()
    }
}
