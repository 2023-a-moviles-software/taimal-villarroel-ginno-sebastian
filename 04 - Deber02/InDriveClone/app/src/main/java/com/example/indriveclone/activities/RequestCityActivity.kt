package com.example.indriveclone.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.indriveclone.R
import com.example.indriveclone.adapters.City.RequestCityAdapter
import com.example.indriveclone.data.Data

class RequestCityActivity : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var recipeAdapter: RequestCityAdapter

    private lateinit var data: Data

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_request_city)
        initComponent()
    }

    private fun initComponent() {
        // Obtener referencia al RecyclerView
        recyclerView = findViewById(R.id.rvRequestCity)
        recyclerView.layoutManager = LinearLayoutManager(this)

        data = Data()

        // Crear y configurar el adaptador
        recipeAdapter = RequestCityAdapter(data.insertDataRequestCity(), this)
        recyclerView.adapter = recipeAdapter
    }
}