package com.example.examen_crud.activities

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.examen_crud.R
import com.example.examen_crud.models.Recipe
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class EditRecipeActivity : AppCompatActivity() {
    private lateinit var firebase: DatabaseReference

    private lateinit var recipeName: EditText
    private lateinit var recipeDescription: EditText
    private lateinit var recipePreparationTime: EditText
    private lateinit var recipeDifficulty: EditText
    private lateinit var recipeServings: EditText

    private lateinit var recipe: Recipe

    private lateinit var updateRecipeButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_recipe)

        initComponents()

        firebase = FirebaseDatabase.getInstance().getReference("Recipes")

        // Obtener la receta seleccionada
        val recipeId = intent.getStringExtra("RECIPE_ID")

        if (recipeId == null) {
            Toast.makeText(this, "Recipe ID is missing", Toast.LENGTH_SHORT).show()
            finish()
            return
        }

        // Usa recipeId para recuperar los detalles de la receta de Firebase
        firebase.child(recipeId).addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                recipe = snapshot.getValue(Recipe::class.java) ?: run {
                    Toast.makeText(this@EditRecipeActivity, "Recipe not found", Toast.LENGTH_SHORT).show()
                    finish()
                    return
                }

                // Configurar los valores iniciales en los campos de edición
                recipeName.setText(recipe.recipeName)
                recipeDescription.setText(recipe.recipeDescription)
                recipePreparationTime.setText(recipe.preparationTime.toString())
                recipeDifficulty.setText(recipe.difficulty)
                recipeServings.setText(recipe.servings.toString())

                updateRecipeButton.isEnabled = true
            }

            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(this@EditRecipeActivity, "Failed to load recipe", Toast.LENGTH_SHORT)
                    .show()
            }
        })


        // Configurar el evento de clic del botón de actualización
        updateRecipeButton.setOnClickListener {
            updateRecipe()
            finish()
        }

    }

    private fun initComponents() {
        recipeName = findViewById(R.id.EditRecipe_etRecipeName)
        recipeDescription = findViewById(R.id.EditRecipe_etRecipeDescription)
        recipePreparationTime = findViewById(R.id.EditRecipe_etPreparationTime)
        recipeDifficulty = findViewById(R.id.EditRecipe_etDifficulty)
        recipeServings = findViewById(R.id.EditRecipe_etServings)

        updateRecipeButton = findViewById(R.id.EditRecipe_btnUpdate)
        updateRecipeButton.isEnabled = false
    }

    private fun updateRecipe() {
        val name = recipeName.text.toString()
        val description = recipeDescription.text.toString()
        val time = recipePreparationTime.text.toString().toIntOrNull()
        val difficulty = recipeDifficulty.text.toString()
        val servings = recipeServings.text.toString().toIntOrNull()

        if (name == recipe.recipeName &&
            description == recipe.recipeDescription &&
            time == recipe.preparationTime &&
            difficulty == recipe.difficulty &&
            servings == recipe.servings
        ) {
            Toast.makeText(this, "No changes were made", Toast.LENGTH_SHORT).show()
            return
        } else {

            // Crear un objeto Recipe con los nuevos valores
            val updatedRecipe = Recipe(
                recipe.recipeId,
                name,
                description,
                time,
                difficulty,
                servings
            )

            // Actualizar la receta en Firebase
            firebase.child(recipe.recipeId!!).setValue(updatedRecipe).addOnCompleteListener {
                Toast.makeText(this, "Recipe updated successfully", Toast.LENGTH_SHORT).show()
                clearEditText()
            }.addOnFailureListener {
                Toast.makeText(this, "Failed to update recipe", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun clearEditText() {
        recipeName.setText("")
        recipeDescription.setText("")
        recipePreparationTime.setText("")
        recipeDifficulty.setText("")
        recipeServings.setText("")
    }
}
