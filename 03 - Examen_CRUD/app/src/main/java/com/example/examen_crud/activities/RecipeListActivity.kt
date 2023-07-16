package com.example.examen_crud.activities

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.examen_crud.R
import com.example.examen_crud.adapters.recipe.RecipeAdapter
import com.example.examen_crud.database.DatabaseSQLite
import com.example.examen_crud.models.Recipe

class RecipeListActivity : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var recipeAdapter: RecipeAdapter

    private lateinit var database: DatabaseSQLite

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recipe_list)
        initComponent()
    }

    private fun initComponent() {
        // Obtener referencia al RecyclerView
        recyclerView = findViewById(R.id.RecipeList_rvRecipe)
        recyclerView.layoutManager = LinearLayoutManager(this)

        database = DatabaseSQLite(this)
        database.insertSampleRecipes()

        // Crear y configurar el adaptador
        recipeAdapter = RecipeAdapter(database.getAllRecipes(), this)
        recyclerView.adapter = recipeAdapter

        recipeAdapter?.updateItems(database.getAllRecipes() as ArrayList<Recipe>)

        // Navigate
        navigateToAddRecipe()
    }

    private fun navigateToAddRecipe() {
        val createRecipeButton = findViewById<Button>(R.id.RecipeList_btnAddRecipe)

        createRecipeButton.setOnClickListener {
            val intent = Intent(this, AddRecipeActivity::class.java)
            startActivity(intent)
        }
    }
}