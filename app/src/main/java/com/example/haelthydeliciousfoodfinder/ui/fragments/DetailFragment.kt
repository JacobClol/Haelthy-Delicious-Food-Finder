package com.example.haelthydeliciousfoodfinder.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import com.bumptech.glide.Glide
import com.example.haelthydeliciousfoodfinder.R
import com.example.haelthydeliciousfoodfinder.databinding.FragmentDetailBinding
import com.example.haelthydeliciousfoodfinder.ui.viewmodels.DetailViewModel
import com.google.android.material.appbar.AppBarLayout
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailFragment : Fragment(), AppBarLayout.OnOffsetChangedListener {

    private var _binding: FragmentDetailBinding? = null
    private val binding get() = _binding!!
    private var scrollRange = -1
    private var isShow = false
    private val args: DetailFragmentArgs by navArgs()
    private val viewModel: DetailViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initCollapsingToolbar()
        setDataInit()
    }

    override fun onOffsetChanged(appBarLayout: AppBarLayout?, verticalOffset: Int) {
        if (scrollRange == -1) {
            scrollRange = appBarLayout?.totalScrollRange ?: -1
        }
        if (scrollRange + verticalOffset == 0) {
            binding.collapsingToolbar.title = args.recipe.title
            isShow = true
        } else {
            binding.collapsingToolbar.title = ""
            isShow = false
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun initCollapsingToolbar() {
        binding.appBar.addOnOffsetChangedListener(this)
        val navController = findNavController()
        val appBarConfiguration = AppBarConfiguration(navController.graph)

        with(binding) {
            collapsingToolbar.title = ""
            appBar.setExpanded(true)
            collapsingToolbar.setupWithNavController(
                binding.toolbar, navController, appBarConfiguration
            )
        }
    }

    private fun setDataInit() {
        viewModel.recipe.observe(viewLifecycleOwner) { recipe ->
            Glide.with(requireContext()).load(recipe.urlImage).placeholder(R.drawable.load)
                .into(binding.thumbnailHeader)
            with(binding) {
                tvTitle.text = recipe.title
                tvCalories.text = recipe.calories.toString()
                val city = "Press to show in map ${recipe.city}"
                tvCity.text = city
                tvSource.text = recipe.source
                tvDiet.text = recipe.dietLabels.toString()
                val portion = "${recipe.yield} portions"
                tvYield.text = portion
                val listIngredients = ArrayList<String>()
                listIngredients.addAll(recipe.ingredientLine)
                val arrayAdapter = ArrayAdapter(
                    requireContext(),
                    android.R.layout.simple_list_item_1,
                    listIngredients
                )
                lvIngredients.adapter = arrayAdapter
                tvCity.setOnClickListener {
                    val action =
                        DetailFragmentDirections.actionDetailFragmentToMapsActivity(recipe.city)
                    findNavController().navigate(action)
                }
            }
        }
        viewModel.error.observe(viewLifecycleOwner) {
            Toast.makeText(requireContext(), it, Toast.LENGTH_SHORT).show()
        }
    }
}