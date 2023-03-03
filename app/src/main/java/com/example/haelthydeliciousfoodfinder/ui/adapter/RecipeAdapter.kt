package com.example.haelthydeliciousfoodfinder.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.haelthydeliciousfoodfinder.R
import com.example.haelthydeliciousfoodfinder.databinding.ItemRecipeBinding
import com.example.haelthydeliciousfoodfinder.domain.models.RecipeItem

class RecipeAdapter(
    private val onRecipeClick: (RecipeItem) -> Unit
) : ListAdapter<RecipeItem, RecipeViewHolder>(RecipeDiffCallBack()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecipeViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_recipe, parent, false)
        return RecipeViewHolder(view, parent.context)
    }

    override fun onBindViewHolder(holder: RecipeViewHolder, position: Int) {
        holder.bin(getItem(position), onRecipeClick)
    }
}

class RecipeDiffCallBack : DiffUtil.ItemCallback<RecipeItem>() {
    override fun areItemsTheSame(oldItem: RecipeItem, newItem: RecipeItem): Boolean {
        return oldItem.title == newItem.title
    }

    override fun areContentsTheSame(oldItem: RecipeItem, newItem: RecipeItem): Boolean {
        return oldItem == newItem
    }

}

class RecipeViewHolder(view: View, context: Context) : RecyclerView.ViewHolder(view) {
    private val binding = ItemRecipeBinding.bind(view)
    private val mContext = context
    fun bin(recipe: RecipeItem, onRecipeClick: (RecipeItem) -> Unit) {
        Glide.with(mContext).load(recipe.urlImage).placeholder(R.drawable.load).into(binding.thumbnail)
        binding.tvTitle.text = recipe.title
        val calories = "Calories: ${recipe.calories}"
        binding.tvCalories.text = calories
        val numIngredient = "Ingredients: ${recipe.ingredientLine.size}"
        binding.tvIngredients.text = numIngredient
        binding.tvPlace.text = recipe.source
        binding.cardView.setOnClickListener {
            onRecipeClick(recipe)
        }
    }
}
