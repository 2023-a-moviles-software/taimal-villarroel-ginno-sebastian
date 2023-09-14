package com.example.examen_crud.activities

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.examen_crud.R
import com.example.examen_crud.models.Recipe
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class AddRecipeActivity : AppCompatActivity() {

    private lateinit var firebase: DatabaseReference

    private lateinit var addRecipeButton: Button

    private lateinit var recipeName: EditText
    private lateinit var recipeDescription: EditText
    private lateinit var recipePreparationTime: EditText
    private lateinit var recipeDifficulty: EditText
    private lateinit var recipeServings: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_recipe)

        initContent()

        firebase = FirebaseDatabase.getInstance().getReference("Recipes")

        addRecipeButton.setOnClickListener {
            addRecipe()
            finish()
        }
    }

    private fun initContent() {
        addRecipeButton = findViewById<Button>(R.id.AddRecipe_btnAdd)
        recipeName = findViewById<EditText>(R.id.AddRecipe_etRecipeName)
        recipeDescription = findViewById<EditText>(R.id.AddRecipe_etRecipeDescription)
        recipePreparationTime = findViewById<EditText>(R.id.AddRecipe_etPreparationTime)
        recipeDifficulty = findViewById<EditText>(R.id.AddRecipe_etDifficulty)
        recipeServings = findViewById<EditText>(R.id.AddRecipe_etServings)
    }

    private fun addRecipe() {
        // Obtener valores de la interfaz
        val recipeId = firebase.push().key!!
        val name = recipeName.text.toString()
        val description = recipeDescription.text.toString()
        val time = recipePreparationTime.text.toString()
        val difficulty = recipeDifficulty.text.toString()
        val servings = recipeServings.text.toString()

        if (
            name.isEmpty() ||
            description.isEmpty() ||
            time.isEmpty() ||
            difficulty.isEmpty() ||
            servings.isEmpty()
        ) {
            Toast.makeText(this, "Please enter required field", Toast.LENGTH_SHORT).show()
        } else {
            val recipe = Recipe(
                recipeId,
                name,
                description,
                time.toInt(),
                difficulty,
                servings.toInt()
            )

            firebase.child(recipeId).setValue(recipe).addOnCompleteListener {
                Toast.makeText(this, "Recipe Added...", Toast.LENGTH_SHORT).show()
                clearEditText()
            }.addOnFailureListener {
                Toast.makeText(this, "Recipe not saved...", Toast.LENGTH_SHORT).show()
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