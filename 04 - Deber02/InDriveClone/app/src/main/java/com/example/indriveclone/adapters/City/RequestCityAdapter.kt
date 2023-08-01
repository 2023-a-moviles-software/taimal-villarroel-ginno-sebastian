package com.example.indriveclone.adapters.City

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.indriveclone.R
import com.example.indriveclone.models.RequestCity

class RequestCityAdapter(
    private var requestCities: List<RequestCity>,
    private val context: Context
) : RecyclerView.Adapter<RequestCityViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RequestCityViewHolder {
        val itemView = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.item_request_city, parent, false)
        return RequestCityViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: RequestCityViewHolder, position: Int) {
        val currentCity = requestCities[position]
        holder.bind(currentCity)
    }

    override fun getItemCount() = requestCities.size
}