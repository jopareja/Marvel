package com.example.marvel.ui.comics.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.example.marvel.data.remote.responses.getComics.ServerComic
import com.example.marvel.databinding.ViewHolderComicBinding

class ComicsAdapter: ListAdapter<ServerComic, ComicsAdapter.ComicViewHolder>(DiffCallBack) {

    class ComicViewHolder(
        private var binding: ViewHolderComicBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(comic: ServerComic) {
            Log.d("JOSE", comic.thumbnail.path+"."+comic.thumbnail.extension)
            Glide.with(itemView).load(comic.thumbnail.path+"."+comic.thumbnail.extension)
                .transform(CenterCrop())
                .into(binding.ivComics)
            binding.tvComics.text = comic.title
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ComicViewHolder {
        return ComicViewHolder(ViewHolderComicBinding.inflate(
            LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: ComicViewHolder, position: Int) {
        val comic = getItem(position)
        holder.bind(comic)
    }

    companion object DiffCallBack : DiffUtil.ItemCallback<ServerComic>() {
        override fun areItemsTheSame(oldItem: ServerComic, newItem: ServerComic): Boolean {
            return oldItem.title == newItem.title
        }

        override fun areContentsTheSame(oldItem: ServerComic, newItem: ServerComic): Boolean {
            return  oldItem.title == newItem.title
        }
    }
}