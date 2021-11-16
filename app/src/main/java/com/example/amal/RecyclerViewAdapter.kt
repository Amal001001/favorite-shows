package com.example.amal

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.amal.databinding.ItemRowBinding

class RecyclerViewAdapter(val fragment:ApiFragment, private val shows: ArrayList<Show>): RecyclerView.Adapter<RecyclerViewAdapter.ItemViewHolder>() {
        class ItemViewHolder(val binding: ItemRowBinding): RecyclerView.ViewHolder(binding.root)
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {

            return ItemViewHolder(
                ItemRowBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            )
        }
        override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
            val show = shows[position]

            holder.binding.apply {
                tvName.text = show.name
                itemRowLayout.setOnClickListener { fragment.addToDB(show.name,show.language,show.summary,show.imageUrl) }
            }
        }
        override fun getItemCount() = shows.size
}