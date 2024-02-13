import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.practice.walmart.model.Country
import com.practice.walmart.network.ApiClient
import com.practice.walmart.network.ApiService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class CountryRepo {
    private val apiService: ApiService = ApiClient.getRetrofit().create(ApiService::class.java)

    suspend fun getCountry(): LiveData<List<Country>> {
        return withContext(Dispatchers.IO) {
            val data = MutableLiveData<List<Country>>()

            try {
                val response = apiService.getCountries()
                if (response.code() == 200) { // Check if the HTTP status code is 200 (OK)
                    data.postValue(response.body())
                } else {
                    // Handle unsuccessful response if needed
                    // Log.e("", "Not Working - Response Code: ${response.code()}")
                    data.postValue(null)
                }
            } catch (e: Exception) {
                data.postValue(null)
                // Log.e("", "Not Working", e)
            }

            data
        }
    }
}
