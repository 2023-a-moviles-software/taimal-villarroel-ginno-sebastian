package com.example.examen_crud.activities

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.example.examen_crud.R

class WelcomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_welcome)

        // Obtener referencia al botón de bienvenida
        val welcomeButton = findViewById<Button>(R.id.welcomeButton)

        // Configurar el evento clic del botón de bienvenida
        welcomeButton.setOnClickListener {
            // Navegar a la siguiente actividad (por ejemplo, RecipeListActivity)
            val intent = Intent(this, RecipeListActivity::class.java)
            startActivity(intent)
            finish() // Finalizar la actividad actual (WelcomeActivity)
        }
    }
}