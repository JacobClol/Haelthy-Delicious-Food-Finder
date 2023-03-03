package com.example.haelthydeliciousfoodfinder.ui.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.example.haelthydeliciousfoodfinder.domain.models.RecipeItem
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    val error = MutableLiveData<String>()
    val recipe = MutableLiveData<RecipeItem>()

    init {
        val recipeArgs: RecipeItem? = savedStateHandle["recipe"]
        if(recipeArgs != null){
            recipe.postValue(recipeArgs)
        } else {
            error.postValue("Not found recipe")
        }
    }
}