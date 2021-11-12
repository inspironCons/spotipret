package com.playground.spotipret.ui.playlist

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.playground.spotipret.databinding.ItemPlaylistBinding
import com.playground.spotipret.model.Playlist

class PlaylistAdapter:RecyclerView.Adapter<PlaylistAdapter.ViewHolder>() {
    private var list:List<Playlist> = listOf()
    inner class ViewHolder(view: ItemPlaylistBinding):RecyclerView.ViewHolder(view.root){
        val title = view.tvTitlePlaylist
        val category = view.tvCategoryPlaylist
    }



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemPlaylistBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = list[position]

        holder.title.text = item.name
        holder.category.text = item.category
    }

    fun setItems(list:List<Playlist>){
        val diffCallback = DiffCallback(this.list,list)
        val diffResult = DiffUtil.calculateDiff(diffCallback)
        this.list = emptyList()
        this.list = list
        diffResult.dispatchUpdatesTo(this)
    }

    override fun getItemCount(): Int = list.size

    private inner class DiffCallback(
        private val oldList:List<Playlist>,
        private val newList:List<Playlist>,
    ):DiffUtil.Callback(){
        override fun getOldListSize(): Int = oldList.size

        override fun getNewListSize(): Int = newList.size

        override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            return oldList[oldItemPosition].id == newList[newItemPosition].id
        }

        override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            val old = oldList[oldItemPosition]
            val new = newList[newItemPosition]

            return old.name == new.name &&
                    old.category == new.category
        }

    }
}