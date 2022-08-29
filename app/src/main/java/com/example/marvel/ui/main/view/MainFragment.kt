package com.example.marvel.ui.main.view

import android.app.AlertDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.marvel.R
import com.example.marvel.databinding.FragmentMainBinding
import com.example.marvel.ui.main.adapter.CharacterAdapter
import com.example.marvel.ui.main.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

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
        lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.uiState.collect {state ->
                    if (state.isLoading) binding.pbMain.visibility = View.VISIBLE else binding.pbMain.visibility = View.GONE
                    if (state.isError) state.requestMessage?.let { showDialog(it) } else characterAdapter.submitList(state.characterList)
                }
            }
        }


    }

    private fun showDialog(message: String) {
        val dialog = AlertDialog.Builder(context)
            .setTitle(R.string.dialog_title)
            .setMessage(message)
            .setNeutralButton(R.string.dialog_cancel) { dialog, _ ->
                dialog.cancel()
            }
            .setPositiveButton(R.string.dialog_try_again) { _, _ ->
                viewModel.getCharacterList(paginatedValue)
            }
            .setCancelable(false)
            .create()
        dialog.show()
    }
}