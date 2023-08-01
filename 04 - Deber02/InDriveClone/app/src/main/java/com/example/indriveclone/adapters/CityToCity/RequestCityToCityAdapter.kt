package com.example.indriveclone.adapters.CityToCity

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.indriveclone.R
import com.example.indriveclone.models.RequestCityToCity

class RequestCityToCityAdapter(
    private var requestCitiesToCities: List<RequestCityToCity>,
    private val context: Context
) : RecyclerView.Adapter<RequestCityToCityViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RequestCityToCityViewHolder {
        val itemView = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.item_request_city_to_city, parent, false)
        return RequestCityToCityViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: RequestCityToCityViewHolder, position: Int) {
        val currentCity = requestCitiesToCities[position]
        holder.bind(currentCity)
    }

    override fun getItemCount() = requestCitiesToCities.size
}