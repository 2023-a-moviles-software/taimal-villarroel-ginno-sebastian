package com.example.examen_crud.adapters.ingredient

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.examen_crud.R
import com.example.examen_crud.models.Ingredient

class IngredientViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    private val ingredientNameTextView: TextView =
        itemView.findViewById(R.id.ItemIngredient_tvIngredientTitle)
    private val ingredientQuantityTextView: TextView =
        itemView.findViewById(R.id.ItemIngredient_tvIngredientQuantity)
    private val measurementUnitTextView: TextView =
        itemView.findViewById(R.id.ItemIngredient_tvIngredientUnit)

    fun bind(ingredient: Ingredient) {
        ingredientNameTextView.text = ingredient.ingredientName
        ingredientQuantityTextView.text = ingredient.ingredientQuantity.toString()
        measurementUnitTextView.text = ingredient.measurementUnit
    }
}