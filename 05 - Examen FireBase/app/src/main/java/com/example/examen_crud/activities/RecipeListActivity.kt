package com.example.examen_crud.activities

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.examen_crud.R
import com.example.examen_crud.adapters.recipe.RecipeAdapter
import com.example.examen_crud.models.Recipe
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class RecipeListActivity : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var recipeAdapter: RecipeAdapter

    private lateinit var firebase: DatabaseReference

    private lateinit var recipesList: ArrayList<Recipe>


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recipe_list)
        initComponent()

        // Navigate
        navigateToAddRecipe()
    }

    private fun initComponent() {
        // Obtener referencia al RecyclerView
        recyclerView = findViewById(R.id.RecipeList_rvRecipe)
        recyclerView.layoutManager = LinearLayoutManager(this)

        firebase = FirebaseDatabase.getInstance().getReference("Recipes")
        recipesList = arrayListOf<Recipe>()

        // Crear y configurar el adaptador
        getRecipeList()
    }


    private fun getRecipeList() {
        firebase.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                recipesList.clear()
                if (snapshot.exists()) {
                    for (recipeSnapshot in snapshot.children) {
                        val recipeData = recipeSnapshot.getValue(Recipe::class.java)
                        recipesList.add(recipeData!!)
                    }
                    recipeAdapter = RecipeAdapter(recipesList)
                    recyclerView.adapter = recipeAdapter
                }
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }
        })
    }

    private fun navigateToAddRecipe() {
        val createRecipeButton = findViewById<Button>(R.id.RecipeList_btnAddRecipe)

        createRecipeButton.setOnClickListener {
            val intent = Intent(this, AddRecipeActivity::class.java)
            startActivity(intent)
        }
    }
}