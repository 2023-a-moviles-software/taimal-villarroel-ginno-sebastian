package com.example.indriveclone.adapters.CityToCity

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.indriveclone.R
import com.example.indriveclone.models.RequestCityToCity

class RequestCityToCityViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    private val title: TextView = itemView.findViewById<TextView>(R.id.requestCityToCity_title)
    private val paymentType: TextView = itemView.findViewById<TextView>(R.id.requestCityToCity_paymentType)
    private val tripType: TextView = itemView.findViewById<TextView>(R.id.requestCityToCity_tripType)
    private val date: TextView = itemView.findViewById<TextView>(R.id.requestCityToCity_date)
    private val hour: TextView = itemView.findViewById<TextView>(R.id.requestCityToCity_hour)
    private val origin: TextView = itemView.findViewById<TextView>(R.id.requestCityToCity_origin)
    private val destiny: TextView = itemView.findViewById<TextView>(R.id.requestCityToCity_destiny)
    private val requestTime: TextView = itemView.findViewById<TextView>(R.id.requestCityToCity_requestTime)
    private val nameUser: TextView = itemView.findViewById<TextView>(R.id.requestCityToCity_nameUser)

    fun bind(requestCityToCity: RequestCityToCity) {
        title.text = requestCityToCity.title
        paymentType.text = requestCityToCity.paymentType
        tripType.text = requestCityToCity.tripType
        date.text = requestCityToCity.date
        hour.text = requestCityToCity.hour
        origin.text = requestCityToCity.origin
        destiny.text = requestCityToCity.destiny
        requestTime.text = requestCityToCity.requestTime
        nameUser.text = requestCityToCity.nameUser
    }
}