package com.practice.walmart

import CountryAdapter
import CountryViewModel
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.practice.walmart.databinding.ActivityMainBinding
import com.practice.walmart.model.Country


class MainActivity : AppCompatActivity() {
    private var viewModel: CountryViewModel = CountryViewModel()

    private lateinit var activityMainBinding: ActivityMainBinding

    private lateinit var countryAdapter: CountryAdapter
    private val countryList: MutableList<Country> = mutableListOf()

    private var currentPage = 1


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



        getCountries()
    }

    private fun setupRecyclerView() {
        activityMainBinding.tvShowRecyclerView.setHasFixedSize(true)
        countryAdapter = CountryAdapter(
            this, countryList
        )
        activityMainBinding.tvShowRecyclerView.adapter = countryAdapter
    }

    private fun getCountries() {
        toggleLoading()
        viewModel.countries.observe(this, Observer { countries ->
            toggleLoading()
            countries?.let { shows ->
                countryList.addAll(shows)
                countryAdapter.notifyDataSetChanged()
                //showToast("Showing")
            }
        })
    }

    private fun toggleLoading() {
        if (currentPage == 1) {
            activityMainBinding.isloading = activityMainBinding.isloading != true
        } else {
            activityMainBinding.isloadingMore = activityMainBinding.isloadingMore != true
        }
    }
}
