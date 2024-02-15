import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.practice.walmart.model.Country
import com.practice.walmart.R
import com.practice.walmart.databinding.CountryListItemBinding

class CountryAdapter(private val context: Context, private var countryList: List<Country>) :
    RecyclerView.Adapter<CountryAdapter.CountryViewHolder>() {

    private var filteredList: List<Country> = countryList

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CountryViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding: CountryListItemBinding = DataBindingUtil.inflate(
            layoutInflater, R.layout.country_list_item, parent, false
        )
        return CountryViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CountryViewHolder, position: Int) {
        holder.bind(filteredList[position])
    }

    override fun getItemCount(): Int {
        return filteredList.size
    }

    fun filterList(filteredList: List<Country>) {
        this.filteredList = filteredList
        notifyDataSetChanged()
    }

    inner class CountryViewHolder(private val binding: CountryListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(country: Country) {
            binding.countryModel = country
            binding.executePendingBindings()
        }
    }
}
