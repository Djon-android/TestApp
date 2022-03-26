package com.example.testapp.presentation

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProvider
import com.example.testapp.R
import com.example.testapp.databinding.FragmentCharacterListBinding
import com.example.testapp.presentation.adapters.CharacterAdapter


class CharacterListFragment : Fragment() {

    private lateinit var viewModel: CharacterViewModel
    var page = 1
    var isLoading = false

    private var _binding: FragmentCharacterListBinding? = null
    private val binding: FragmentCharacterListBinding
        get() = _binding ?: throw RuntimeException("FragmentCharacterListBinding is null")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCharacterListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this)[CharacterViewModel::class.java]
        (requireActivity() as AppCompatActivity).supportActionBar?.title =
            requireActivity().resources.getString(R.string.character)
        val adapter = CharacterAdapter(requireContext())
        adapter.onReachEndListener = object : CharacterAdapter.OnReachEndListener {
            override fun onReachEnd() {
                if (!isLoading) {
                    viewModel.loadData(page++)
                }
            }
        }
        viewModel.loadData(page++)
        binding.rvCharacterList.adapter = adapter
        viewModel = ViewModelProvider(this)[CharacterViewModel::class.java]
        viewModel.characterList.observe(viewLifecycleOwner) {
            if (it != null && it.isNotEmpty()) {
                Log.i("itit", it.size.toString())
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


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {

        fun newInstance() = CharacterListFragment()

    }
}