package com.example.testapp.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.testapp.R
import com.example.testapp.databinding.FragmentCharacterListBinding
import com.example.testapp.presentation.adapters.CharacterAdapter


class CharacterListFragment : Fragment() {

    private lateinit var viewModel: CharacterViewModel
    private var page = 2
    private var isLoading = false

    private var _binding: FragmentCharacterListBinding? = null
    private val binding: FragmentCharacterListBinding
        get() = _binding ?: throw RuntimeException("FragmentCharacterListBinding is null")

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCharacterListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setTitle()
        viewModel = ViewModelProvider(this)[CharacterViewModel::class.java]
        val adapter = CharacterAdapter(requireContext())
        setListenerOnAdapter(adapter)
        binding.rvCharacterList.adapter = adapter
        setObserve(adapter)
    }

    private fun setObserve(adapter: CharacterAdapter) {
        viewModel.characterList.observe(viewLifecycleOwner) {
            if (it != null && it.isNotEmpty()) {
                adapter.submitList(it)
            }
        }
        viewModel.isLoading.observe(viewLifecycleOwner) {
            isLoading = it
            if (it) {
                binding.progressBar.visibility = View.VISIBLE
            } else {
                binding.progressBar.visibility = View.INVISIBLE
            }
        }
    }

    private fun setListenerOnAdapter(adapter: CharacterAdapter) {
        adapter.onReachEndListener = object : CharacterAdapter.OnReachEndListener {
            override fun onReachEnd() {
                if (!isLoading) {
                    viewModel.loadData(page++)
                }
            }
        }
    }

    private fun setTitle() {
        (requireActivity() as AppCompatActivity).supportActionBar?.title =
            requireActivity().resources.getString(R.string.character)
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {

        fun newInstance() = CharacterListFragment()

    }
}