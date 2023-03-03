package com.example.haelthydeliciousfoodfinder.ui.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.haelthydeliciousfoodfinder.domain.models.RecipeItem
import com.example.haelthydeliciousfoodfinder.domain.models.RecipeParams
import com.example.haelthydeliciousfoodfinder.domain.usecases.GetRecipeByQueryUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val getRecipeByQueryUseCase: GetRecipeByQueryUseCase
) : ViewModel() {

    val listRecipesRemote = MutableLiveData<List<RecipeItem>>()
    val isLoading = MutableLiveData<Boolean>()
    val error = MutableLiveData<String>()

    init {
        fetchRecipeByQuery("Salad mix")
    }

    fun fetchRecipeByQuery(query: String){
        viewModelScope.launch {
            isLoading.postValue(true)
            val response = getRecipeByQueryUseCase(
                RecipeParams(
                   query = query
                )
            )
            if (response.isNotEmpty()){
                listRecipesRemote.postValue(response)
            } else {
                error.postValue("Not found recipes")
            }
            isLoading.postValue(false)
        }
    }
}