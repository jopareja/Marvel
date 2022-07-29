package com.example.marvel.ui.comics.view

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.marvel.databinding.FragmentComicsBinding
import com.example.marvel.ui.comics.ComicsViewModel
import com.example.marvel.ui.comics.adapter.ComicsAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ComicsFragment : Fragment() {

    private var _binding: FragmentComicsBinding? = null
    private val binding get() = _binding!!
    private lateinit var comicsAdapter: ComicsAdapter
    private val viewModel: ComicsViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentComicsBinding.inflate(inflater, container, false)
        comicsAdapter = ComicsAdapter()
        binding.rvComics.adapter = comicsAdapter
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getComics(provideId())
        viewModel.comicList.observe(viewLifecycleOwner) { currentList ->
            comicsAdapter.submitList(currentList)
        }
    }

    private fun provideId(): Int {
        var characterId: Int = 1011334
        arguments?.let {
            it.getInt(ID).let { it2 ->
                characterId = it2
            }
        }
        return characterId
    }

    companion object {
        const val ID = "id"
    }
}