package com.example.haelthydeliciousfoodfinder.ui.fragments

import android.content.Context
import android.os.Bundle
import android.view.*
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.core.view.MenuProvider
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import com.example.haelthydeliciousfoodfinder.R
import com.example.haelthydeliciousfoodfinder.databinding.FragmentSearchBinding
import com.example.haelthydeliciousfoodfinder.domain.models.RecipeItem
import com.example.haelthydeliciousfoodfinder.ui.adapter.RecipeAdapter
import com.example.haelthydeliciousfoodfinder.ui.viewmodels.SearchViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SearchFragment : Fragment() {

    private var _binding: FragmentSearchBinding? = null
    private val binding get() = _binding!!
    private val viewModel: SearchViewModel by viewModels()
    private lateinit var adapter: RecipeAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSearchBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initToolbar()
        initMenuProvider()
        setDataInit()
        adapter = RecipeAdapter(onRecipeClick = {
            goToDetailRecipe(it)
        })
        binding.rvListLastSearch.adapter = adapter
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun initToolbar() {
        val navCollection = findNavController()
        val appBarConfiguration = AppBarConfiguration(navCollection.graph)
        binding.toolbarSearch.setupWithNavController(navCollection, appBarConfiguration)
    }

    private fun initMenuProvider() {
        val menuProvider = object : MenuProvider {
            override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
                menuInflater.inflate(R.menu.search_menu, menu)
                val searchItem = menu.findItem(R.id.app_bar_search)
                val searchView: SearchView = searchItem.actionView as SearchView
                searchView.queryHint = getString(R.string.search)
                searchView.setOnCloseListener { true }
                searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
                    override fun onQueryTextSubmit(query: String?): Boolean {
                        query?.let {
                            viewModel.fetchRecipeByQuery(it)
                        }
                        hideKeyboard()
                        return true
                    }

                    override fun onQueryTextChange(query: String?): Boolean {
                        return false
                    }
                })
            }

            override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
                return false
            }

        }
        binding.toolbarSearch.addMenuProvider(menuProvider, viewLifecycleOwner)
    }

    private fun hideKeyboard() {
        val imm =
            requireContext().getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(binding.root.windowToken, 0)
    }

    private fun setDataInit() {
        viewModel.listRecipesRemote.observe(viewLifecycleOwner) {
            val totalResultSubtitle = "${it.size} Recipes"
            binding.tvResult.text = totalResultSubtitle
            adapter.submitList(it)
        }
        viewModel.error.observe(viewLifecycleOwner) {
            Toast.makeText(requireContext(), it, Toast.LENGTH_SHORT).show()
        }
        viewModel.isLoading.observe(viewLifecycleOwner) {
            binding.progressBar.isVisible = it
        }
    }

    private fun goToDetailRecipe(recipe: RecipeItem){
        val action = SearchFragmentDirections.actionSearchFragmentToDetailFragment(recipe)
        findNavController().navigate(action)
    }
}