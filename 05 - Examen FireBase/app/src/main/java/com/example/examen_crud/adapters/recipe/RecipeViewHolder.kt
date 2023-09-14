package com.example.examen_crud.adapters.recipe

import android.content.Intent
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.examen_crud.R
import com.example.examen_crud.activities.EditRecipeActivity
import com.example.examen_crud.models.Recipe
import com.google.firebase.database.FirebaseDatabase

class RecipeViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    private val firebase = FirebaseDatabase.getInstance().getReference("Recipes")

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

    fun bind(recipe: Recipe) {
        recipeNameTextView.text = recipe.recipeName
        recipeTimeTextView.text = recipe.preparationTime.toString()
        recipeDifficultyTextView.text = recipe.difficulty
        recipeServingsTextView.text = recipe.servings.toString()

        editButton.setOnClickListener {
            // Acción al hacer clic en el botón de edición
            val context = itemView.context
            val intent = Intent(context, EditRecipeActivity::class.java)
            intent.putExtra("RECIPE_ID", recipe.recipeId)
            context.startActivity(intent)
        }

        deleteButton.setOnClickListener {
            // Acción al hacer clic en el botón de eliminación
            if (recipe.recipeId != null) {
                firebase.child(recipe.recipeId).removeValue().addOnSuccessListener {
                    Toast.makeText(
                        itemView.context,
                        "Recipe deleted successfully",
                        Toast.LENGTH_SHORT
                    ).show()
                }.addOnFailureListener {
                    Toast.makeText(itemView.context, "Failed to delete recipe", Toast.LENGTH_SHORT)
                        .show()
                }
            }
        }

    }
}