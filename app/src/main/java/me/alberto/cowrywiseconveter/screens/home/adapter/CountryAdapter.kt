package me.alberto.cowrywiseconveter.screens.home.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Filter
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import com.bumptech.glide.Glide
import me.alberto.cowrywiseconveter.R
import me.alberto.cowrywiseconveter.data.domain.model.Country

class CountryAdapter(
    context: Context,
    val list: List<Country>
) : ArrayAdapter<Country>(context, 0, list) {
    private val contryList = ArrayList<Country>()

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        return createView(position, convertView, parent)
    }

    private fun createView(position: Int, convertView: View?, parent: ViewGroup): View {
        val item = getItem(position)
        val view = convertView as? ConstraintLayout ?: LayoutInflater.from(parent.context)
            .inflate(R.layout.simple_text_item, parent, false) as ConstraintLayout
        val countryFlag = view.findViewById<ImageView>(R.id.country_flag)
        val countryCode = view.findViewById<TextView>(R.id.country_code)

        if (item != null){
            countryCode.text = item?.code
            Glide.with(view.context)
                .load("https://www.countryflags.io/${item?.image?.subSequence(0, 2)}/flat/64.png")
                .into(countryFlag)
        }

        return view
    }

    override fun getFilter(): Filter {
        return countryFilter
    }

    private val countryFilter: Filter = object : Filter() {
        override fun performFiltering(constraint: CharSequence?): FilterResults? {
            val results = FilterResults()
            val suggestions: MutableList<Country> = ArrayList()
            if (constraint == null || constraint.isEmpty()) {
                suggestions.addAll(contryList)
            } else {
                val filterPattern = constraint.toString().toLowerCase().trim { it <= ' ' }
                for (item in contryList) {
                    if (item.code.toLowerCase().contains(filterPattern)) {
                        suggestions.add(item)
                    }
                }
            }
            results.values = suggestions
            results.count = suggestions.size
            return results
        }

        override fun publishResults(constraint: CharSequence?, results: FilterResults) {
            clear()
            addAll(results.values as ArrayList<out Country>)
            notifyDataSetChanged()
        }

        override fun convertResultToString(resultValue: Any): CharSequence? {
            return (resultValue as Country).code
        }
    }
}