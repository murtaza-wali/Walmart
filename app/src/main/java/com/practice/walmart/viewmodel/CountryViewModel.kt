import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.practice.walmart.model.Country
import kotlinx.coroutines.launch

class CountryViewModel : ViewModel() {
    private val countryRepo: CountryRepo = CountryRepo()

    private val _countries = MutableLiveData<List<Country>>()
    val countries: LiveData<List<Country>>
        get() = _countries

    init {
        // Load countries when the ViewModel is initialized
        fetchCountries()
    }

    private fun fetchCountries() {
        viewModelScope.launch {
            try {
                val countriesResponse = countryRepo.getCountry()
                _countries.value = countriesResponse.value // Update LiveData with fetched data
            } catch (e: Exception) {
                // Handle exception
                _countries.value = emptyList() // Or handle error state accordingly
            }
        }
    }
}
