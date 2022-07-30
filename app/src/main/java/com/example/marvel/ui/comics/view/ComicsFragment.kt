package com.example.marvel.ui.comics.view

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.marvel.R
import com.example.marvel.databinding.FragmentComicsBinding
import com.example.marvel.ui.comics.ComicsViewModel
import com.example.marvel.ui.comics.adapter.ComicsAdapter
import com.example.marvel.ui.main.viewmodel.HttpStatus
import com.google.android.material.dialog.MaterialAlertDialogBuilder
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
            if (currentList.isNullOrEmpty()) {
                showDialog()
            } else {
                comicsAdapter.submitList(currentList)
            }
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

    private fun showDialog() {
        var message: String
        viewModel.requestStatus.observe(viewLifecycleOwner) {
            message = when (it) {
                HttpStatus.GenericError -> getString(R.string.dialog_generic_error)
                HttpStatus.HTTP400 -> getString(R.string.dialog_http400)
                HttpStatus.HTTP500 -> getString(R.string.dialog_http500)
                HttpStatus.IOException -> getString(R.string.dialog_io_exception)
            }
            context?.let { context ->
                MaterialAlertDialogBuilder(context)
                    .setTitle(resources.getString(R.string.dialog_title))
                    .setMessage(message)
                    .setNeutralButton(resources.getString(R.string.dialog_cancel)) { dialog, _ ->
                        dialog.cancel()
                    }
                    .setPositiveButton(resources.getString(R.string.dialog_try_again)) { dialog, _ ->
                        dialog.cancel()
                        viewModel.getComics(provideId())
                    }
                    .show()
            }
        }
    }

    companion object {
        const val ID = "id"
    }
}