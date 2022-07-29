package com.example.marvel.ui.comics.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.marvel.databinding.FragmentComicsBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ComicsFragment : Fragment() {

    private var _binding: FragmentComicsBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentComicsBinding.inflate(inflater, container, false)
        return binding.root
    }

}