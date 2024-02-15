package com.practice.walmart


import CountryAdapter
import CountryViewModel
import android.content.Context
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.practice.walmart.R
import com.practice.walmart.databinding.ActivityMainBinding
import com.practice.walmart.model.Country



import java.util.*

class MainActivity : AppCompatActivity() {
    private lateinit var viewModel: CountryViewModel
    private lateinit var activityMainBinding: ActivityMainBinding
    private lateinit var countryAdapter: CountryAdapter
    private val countryList: MutableList<Country> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        initViewModel()
        init()
    }

    private fun initViewModel() {
        viewModel = ViewModelProvider(this).get(CountryViewModel::class.java)
    }

    private fun init() {
        setupRecyclerView()
        observeCountries()
        setupSearchListener()
        setupSearchIconClickListener()
        activityMainBinding.editTextSearch.visibility = View.GONE
    }

    private fun setupRecyclerView() {
        activityMainBinding.tvShowRecyclerView.setHasFixedSize(true)
        countryAdapter = CountryAdapter(this, countryList)
        activityMainBinding.tvShowRecyclerView.adapter = countryAdapter
    }

    private fun observeCountries() {
        viewModel.countries.observe(this, Observer { countries ->
            countries?.let { shows ->
                countryList.clear()
                countryList.addAll(shows)
                countryAdapter.notifyDataSetChanged()
            }
        })
    }

    private fun setupSearchListener() {
        activityMainBinding.editTextSearch.visibility = View.VISIBLE
        activityMainBinding.editTextSearch.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                val searchText = s.toString().toLowerCase(Locale.getDefault())

                Log.d("Search", "Search Text: $searchText")
                val filteredList = if (searchText.isNotEmpty()) {
                    val filtered = countryList.filter { country ->
                        // Check if either the country name or capital contains the search text
                        country.name.toLowerCase(Locale.getDefault()).contains(searchText) ||
                                country.capital.toLowerCase(Locale.getDefault()).contains(searchText)
                    }
                    // Add debug statement to check the filtered list
                    Log.d("Search", "Filtered List: $filtered")
                    filtered
                } else {
                    countryList
                }
                // Add debug statement to check the size of filtered list
                Log.d("Search", "Filtered List Size: ${filteredList.size}")
                countryAdapter.filterList(filteredList)
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                // Not used
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                // Not used
            }
        })
    }

    private fun setupSearchIconClickListener() {
        activityMainBinding.imageViewSearch.setOnClickListener {
            // Toggle visibility of EditText and hide TextView and ImageView
            activityMainBinding.editTextSearch.visibility = View.VISIBLE
            activityMainBinding.textViewCountriesList.visibility = View.GONE
            activityMainBinding.imageViewSearch.visibility = View.GONE
            activityMainBinding.closeSearch.visibility = View.VISIBLE
            activityMainBinding.editTextSearch.requestFocus()

            val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.showSoftInput(activityMainBinding.editTextSearch, InputMethodManager.SHOW_IMPLICIT)
    }
        activityMainBinding.closeSearch.setOnClickListener{
            activityMainBinding.editTextSearch.visibility = View.GONE
            activityMainBinding.textViewCountriesList.visibility = View.VISIBLE
            activityMainBinding.imageViewSearch.visibility = View.VISIBLE
            activityMainBinding.closeSearch.visibility = View.GONE

        }

        activityMainBinding.editTextSearch.setOnClickListener {
            // Toggle visibility of EditText and show TextView and ImageView
            activityMainBinding.editTextSearch.visibility = View.GONE
            activityMainBinding.textViewCountriesList.visibility = View.VISIBLE
            activityMainBinding.imageViewSearch.visibility = View.VISIBLE
            activityMainBinding.closeSearch.visibility = View.GONE
        }
    }
}
