package com.example.examen_crud.adapters.recipe

import android.content.Context
import android.content.Intent
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.examen_crud.R
import com.example.examen_crud.activities.EditRecipeActivity
import com.example.examen_crud.database.DatabaseSQLite
import com.example.examen_crud.models.Recipe

class RecipeViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    // Edit Button
    private val editButton: Button =
        itemView.findViewById<Button>(R.id.RecipeList_Item_btnEditRecipe)

    // Delete Button
    private val deleteButton: Button =
        itemView.findViewById<Button>(R.id.RecipeList_Item_btnDeleteRecipe)

    // Atributes
    private val recipeNameTextView: TextView =
        itemView.findViewById(R.id.RecipeList_Item_tvRecipeTitle)
    private val recipeTimeTextView: TextView =
        itemView.findViewById(R.id.RecipeList_Item_tvRecipeTime)
    private val recipeDifficultyTextView: TextView =
        itemView.findViewById(R.id.RecipeList_Item_tvRecipeDifficulty)
    private val recipeServingsTextView: TextView =
        itemView.findViewById(R.id.RecipeList_Item_tvRecipeServings)

    fun bind(recipe: Recipe, context: Context) {
        recipeNameTextView.text = recipe.recipeName
        recipeTimeTextView.text = recipe.preparationTime.toString()
        recipeDifficultyTextView.text = recipe.difficulty
        recipeServingsTextView.text = recipe.servings.toString()

        editButton.setOnClickListener {
            // Acción al hacer clic en el botón de edición
            val intent = Intent(context, EditRecipeActivity::class.java)
            intent.putExtra("recipe", recipe)
            context.startActivity(intent)
        }

        deleteButton.setOnClickListener {
            // Acción al hacer clic en el botón de eliminación
            val database = DatabaseSQLite(context)
            val success = database.deleteRecipe(recipe.recipeId)

            if (success > -1) {
                Toast.makeText(context, "Recipe Deleted...", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(context, "Failed to delete recipe", Toast.LENGTH_SHORT).show()
            }
        }

    }
}