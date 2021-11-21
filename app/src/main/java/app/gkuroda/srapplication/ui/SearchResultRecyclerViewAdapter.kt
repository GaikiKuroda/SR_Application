package app.gkuroda.srapplication.ui

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import app.gkuroda.srapplication.databinding.ViewResultCellBinding

class SearchResultRecyclerViewAdapter(
    context: Context
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    var resultItems: List<String> = listOf()

    private val inflater = LayoutInflater.from(context)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return SearchResultItemViewHolder(ViewResultCellBinding.inflate(inflater, parent, false))
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val searchResultItemViewHolder = holder as SearchResultItemViewHolder
        Log.e("ta", "${resultItems[position]}")
        searchResultItemViewHolder.bind(resultItems[position])
    }

    override fun getItemCount(): Int = resultItems.size

    /**
     * 結果のセルのViewHolder
     */
    class SearchResultItemViewHolder(
        val binding: ViewResultCellBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(nameString: String) {
            binding.name = nameString
            binding.executePendingBindings()
        }
    }

}