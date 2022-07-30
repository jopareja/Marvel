package com.example.marvel.ui.main.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.example.marvel.R
import com.example.marvel.databinding.ViewHolderItemBinding
import com.example.marvel.domain.data.Character
import com.example.marvel.ui.main.view.MainFragmentDirections

class CharacterAdapter: ListAdapter<Character, CharacterAdapter.CharacterViewHolder>(DiffCallBack) {

    class CharacterViewHolder(
        private var binding: ViewHolderItemBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(character: Character) {
            Glide.with(itemView).load(character.imageUrl)
                .transform(CenterCrop())
                .into(binding.ivSuperhero)
            binding.tvSuperhero.text = character.title
            if (character.description == "") {
                binding.tvSuperHeroDescription.text = itemView.context.getString(R.string.character_null_description)
            } else {
                binding.tvSuperHeroDescription.text = character.description
            }
            binding.cvMain.setOnClickListener {
                val action = MainFragmentDirections.actionMainFragmentToComicsFragment(
                    id = character.id
                )
                binding.cvMain.findNavController().navigate(action)
            }
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
