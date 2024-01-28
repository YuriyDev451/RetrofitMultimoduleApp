package com.yuriyyg.flights

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Recycler
import androidx.viewbinding.ViewBinding
import com.yuriyyg.entities.uimodel.FlightListUIModel
import com.yuriyyg.flights.databinding.ListItemBinding

class SearchListAdapter(val onClick: (FlightListUIModel)->Unit): RecyclerView.Adapter<SearchListViewHolder>() {

    private val differ = AsyncListDiffer(this, diffCallback)


    fun setData(items: List<FlightListUIModel>){
        differ.submitList(items)
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchListViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ListItemBinding.inflate(inflater, parent, false)
        return SearchListViewHolder(binding, onClick)
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    override fun onBindViewHolder(holder: SearchListViewHolder, position: Int) {
        differ.currentList.getOrNull(position)?.let {
            holder.bind(it)
        }
    }

    companion object{
        private val diffCallback = object : DiffUtil.ItemCallback<FlightListUIModel>(){
            override fun areItemsTheSame(
                oldItem: FlightListUIModel,
                newItem: FlightListUIModel
            ): Boolean {
                return oldItem.enuid==newItem.enuid
            }

            override fun areContentsTheSame(
                oldItem: FlightListUIModel,
                newItem: FlightListUIModel
            ): Boolean {
                return oldItem==newItem
            }

        }

    }

}

class SearchListViewHolder(private val binding: ListItemBinding, val onClick: (FlightListUIModel)->Unit): RecyclerView.ViewHolder(binding.root){

    fun bind(viewModel: FlightListUIModel){
        binding.txt.text = viewModel.airlineName
        binding.root.setOnClickListener{
            onClick(viewModel)
        }
    }
}