package com.example.marvel.ui.main.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.example.marvel.databinding.ViewHolderItemBinding
import com.example.marvel.domain.data.Character

class CharacterAdapter: ListAdapter<Character, CharacterAdapter.CharacterViewHolder>(DiffCallBack) {

    class CharacterViewHolder(
        private var binding: ViewHolderItemBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(character: Character) {
            Log.d("JOSE", character.imageUrl)
            Glide.with(itemView).load(character.imageUrl)
                .transform(CenterCrop())
                .into(binding.ivSuperhero)
            binding.tvSuperhero.text = character.title
            binding.tvSuperHeroDescription.text = character.description
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharacterViewHolder {
        return CharacterViewHolder(ViewHolderItemBinding.inflate(
            LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: CharacterViewHolder, position: Int) {
        val character = getItem(position)
        holder.bind(character)
    }

    companion object DiffCallBack : DiffUtil.ItemCallback<Character>() {
        override fun areItemsTheSame(oldItem: Character, newItem: Character): Boolean {
            return oldItem.title == newItem.title
        }

        override fun areContentsTheSame(oldItem: Character, newItem: Character): Boolean {
            return oldItem.imageUrl == newItem.imageUrl
        }
    }
}