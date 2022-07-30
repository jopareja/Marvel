package com.example.marvel.ui.main.view

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.marvel.R
import com.example.marvel.databinding.FragmentMainBinding
import com.example.marvel.ui.main.adapter.CharacterAdapter
import com.example.marvel.ui.main.viewmodel.HttpStatus
import com.example.marvel.ui.main.viewmodel.MainViewModel
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainFragment : Fragment() {

    private var _binding: FragmentMainBinding? = null
    private val binding get() = _binding!!
    private val viewModel: MainViewModel by viewModels()
    private lateinit var characterAdapter: CharacterAdapter
    private lateinit var layoutManager: GridLayoutManager
    var paginatedValue = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMainBinding.inflate(inflater, container, false)
        characterAdapter = CharacterAdapter()
        layoutManager = binding.rvMain.layoutManager as GridLayoutManager
        binding.rvMain.adapter = characterAdapter
        binding.rvMain.addOnScrollListener(object: RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                val lastVisibleItem = layoutManager.findLastCompletelyVisibleItemPosition()
                if (lastVisibleItem >= 19) {
                    binding.rvMain.removeOnScrollListener(this)
                    paginatedValue+= 20
                    viewModel.getCharacterList(paginatedValue)
                    binding.rvMain.addOnScrollListener(this)
                }
            }
        })
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getCharacterList(paginatedValue)
        viewModel.characterList.observe(viewLifecycleOwner) { currentList ->
            if (currentList.isNullOrEmpty()) {
                showDialog()
            } else {
                characterAdapter.submitList(currentList)
            }
        }
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
                    .setPositiveButton(resources.getString(R.string.dialog_try_again)) {dialog, _ ->
                        dialog.cancel()
                        viewModel.getCharacterList(paginatedValue)
                    }
                    .show()
            }
        }
    }
}