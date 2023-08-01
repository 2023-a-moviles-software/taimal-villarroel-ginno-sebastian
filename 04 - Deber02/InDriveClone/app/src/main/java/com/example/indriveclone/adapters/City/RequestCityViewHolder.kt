package com.example.indriveclone.adapters.City

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.indriveclone.R
import com.example.indriveclone.models.RequestCity

class RequestCityViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    private val nameUser: TextView = itemView.findViewById<TextView>(R.id.requestCity_nameUser)
    private val score: TextView = itemView.findViewById<TextView>(R.id.requestCity_score)
    private val trip: TextView = itemView.findViewById<TextView>(R.id.requestCity_trip)
    private val requestTime: TextView =
        itemView.findViewById<TextView>(R.id.requestCity_requestTime)
    private val origin: TextView = itemView.findViewById<TextView>(R.id.requestCity_origin)
    private val parishOrigin: TextView =
        itemView.findViewById<TextView>(R.id.requestCity_parishOrigin)
    private val destiny: TextView = itemView.findViewById<TextView>(R.id.requestCity_destiny)
    private val parishDestiny: TextView =
        itemView.findViewById<TextView>(R.id.requestCity_parishDestiny)
    private val money: TextView = itemView.findViewById<TextView>(R.id.requestCity_money)
    private val distance: TextView = itemView.findViewById<TextView>(R.id.requestCity_distance)

    fun bind(requestCity: RequestCity) {
        nameUser.text = requestCity.name
        score.text = requestCity.score.toString()
        trip.text = requestCity.trips
        requestTime.text = requestCity.requestTime.toString()
        origin.text = requestCity.origin
        parishOrigin.text = requestCity.parishOrigin
        destiny.text = requestCity.destiny
        parishDestiny.text = requestCity.parishDestiny
        money.text = requestCity.money.toString()
        distance.text = requestCity.distance.toString()
    }
}