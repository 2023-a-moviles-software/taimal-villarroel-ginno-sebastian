package com.example.indriveclone

import android.content.Intent
import android.os.Bundle
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import com.example.indriveclone.activities.RequestCityActivity
import com.example.indriveclone.activities.RequestCityToCityActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Obtén una referencia al LinearLayout con id=titleCity
        val titleCityLayout = findViewById<LinearLayout>(R.id.titleCity)

        // Agrega el OnClickListener al LinearLayout
        titleCityLayout.setOnClickListener {
            // Aquí inicias la actividad RequestCityActivity
            val intent = Intent(this, RequestCityActivity::class.java)
            startActivity(intent)
        }

        // Obtén una referencia al LinearLayout con id=titleCityToCity
        val titleCityToCityLayout = findViewById<LinearLayout>(R.id.titleCityToCity)

        // Agrega el OnClickListener al LinearLayout
        titleCityToCityLayout.setOnClickListener {
            // Aquí inicias la actividad RequestCityToCityActivity
            val intent = Intent(this, RequestCityToCityActivity::class.java)
            startActivity(intent)
        }
    }
}