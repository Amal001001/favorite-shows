package com.example.amal

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.amal.database.DBShows
import com.example.amal.databinding.ItemRow2Binding

class ShowsAdapter (private val fragment: DbFragment): RecyclerView.Adapter<ShowsAdapter.ItemViewHolder>() {
        private var items = emptyList<DBShows>()
        class ItemViewHolder(val binding: ItemRow2Binding): RecyclerView.ViewHolder(binding.root)

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShowsAdapter.ItemViewHolder {
            return ItemViewHolder(
                ItemRow2Binding.inflate(LayoutInflater.from(parent.context), parent, false)
            )
        }

        override fun onBindViewHolder(holder: ShowsAdapter.ItemViewHolder, position: Int) {
            val item = items[position]

            holder.binding.apply {
                tvShowName.text = item.name
                tvShowLang.text = item.language
                if(item.imageUrl == ""){
                    imageView.setImageResource(R.drawable.ic_nullimage)
                }
                else{
                Glide.with(fragment).load(item.imageUrl).into(imageView)
                }

                ItemRow2Layout.setOnClickListener { fragment.toastSummary(item.summary) }

                btnDelete.setOnClickListener {
                    fragment.deleteDialog(item.id)
                }
            }
        }

        override fun getItemCount() = items.size

        @SuppressLint("NotifyDataSetChanged")
        fun update(items: List<DBShows>){
            this.items = items
            notifyDataSetChanged()
        }
}